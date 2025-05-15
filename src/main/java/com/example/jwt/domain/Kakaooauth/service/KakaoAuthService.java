package com.example.jwt.domain.Kakaooauth.service;

import com.example.jwt.domain.Kakaooauth.presentation.dto.response.KakaoTokenRes;
import com.example.jwt.domain.Kakaooauth.presentation.dto.request.KakaoUserReq;
import com.example.jwt.domain.auth.presentation.dto.response.TokenRes;
import com.example.jwt.domain.user.model.ProfileImage;
import com.example.jwt.domain.user.model.User;
import com.example.jwt.domain.user.model.enums.UserRole;
import com.example.jwt.domain.user.model.repo.ProfileImageJpaRepo;
import com.example.jwt.domain.user.service.UserService;
import com.example.jwt.global.config.KakaoConfig;
import com.example.jwt.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoConfig kakaoConfig;
    private final UserService userService;
    private final ProfileImageJpaRepo ProfileImageRepo;
    private final JwtProvider jwtProvider;
    private final WebClient webClient = WebClient.create("https://kauth.kakao.com");

    @Transactional
    public TokenRes loginWithKakao(String code) {
        KakaoTokenRes token = getAccessToken(code);
        KakaoUserReq userInfo = getUserInfo(token.access_token());
        upsertUser(userInfo);
        return jwtProvider.generateToken(userInfo.id().toString());
    }

    private KakaoTokenRes getAccessToken(String code) {
        return webClient.post()
                .uri("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters
                        .fromFormData("grant_type", "authorization_code")
                        .with("client_id", kakaoConfig.getClientId())
                        .with("redirect_uri", kakaoConfig.getRedirectUri())
                        .with("code", code)
                        .with("client_secret", kakaoConfig.getClientSecret()))
                .retrieve()
                .bodyToMono(KakaoTokenRes.class)
                .block();
    }

    private KakaoUserReq getUserInfo(String accessToken) {
        return webClient.get()
                .uri("/v2/user/me")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserReq.class)
                .block();
    }

    private void upsertUser(KakaoUserReq userInfo) {
        if (userService.existsById(userInfo.id())){
            updateUser(userInfo);
        }else {
            saveUser(userInfo);
        }
    }

    private void saveUser(KakaoUserReq kakaoUser) {
        User user = User.builder()
                .userId(kakaoUser.id())
                .nickname(kakaoUser.nickname())
                .level(1)
                .exp(0)
                .credit(0)
                .snowflakeCapacity(0)
                .storeRestock(0)//todo 이거 기본값 뭘로 설정?
                .creditCollect(0)
                .dropCount(0)
                .role(UserRole.USER)
                .build();
        userService.save(user);

        ProfileImage profileImage = ProfileImage.builder().
                user(user).
                imageFile(kakaoUser.profileImage())//todo 이거 Url 이여야함
                .build();
        ProfileImageRepo.save(profileImage);

    }

    private void updateUser(KakaoUserReq kakaoUser) {

        //todo 업데이트 어떻게? 이미지만?
    }
}
