package com.example.jwt.domain.Kakaooauth.presentation;

import com.example.jwt.domain.auth.presentation.dto.response.TokenRes;
import com.example.jwt.domain.Kakaooauth.service.KakaoAuthService;
import com.example.jwt.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoAuthController {

    private final KakaoAuthService kakaoOAuthService;

    @GetMapping("/code")
    public  ResponseEntity<BaseResponse<TokenRes>> kakaoCode(@RequestParam("code") String code) {
        return BaseResponse.of(kakaoOAuthService.loginWithKakao(code));
    }
}

