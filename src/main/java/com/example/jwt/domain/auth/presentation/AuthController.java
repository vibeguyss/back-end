package com.example.jwt.domain.auth.presentation;

import com.example.jwt.domain.Kakaooauth.presentation.dto.request.KakaoUserReq;
import com.example.jwt.domain.auth.presentation.dto.request.RefreshReq;
import com.example.jwt.domain.auth.presentation.dto.request.SingInReq;
import com.example.jwt.domain.auth.presentation.dto.response.TokenRes;
import com.example.jwt.domain.auth.usecase.AuthUseCase;
import com.example.jwt.global.common.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase authUseCase;

    @Operation(summary = "테스트용 입니다")
    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse<String>> signUp(@RequestBody KakaoUserReq req) {
        authUseCase.signup(req);
        return BaseResponse.of("성공");
    }

    @Operation(summary = "테스트용 입니다")
    @PostMapping("/sign-in")
    public ResponseEntity<BaseResponse<TokenRes>> login(@RequestBody SingInReq req) {
        return BaseResponse.of(authUseCase.signIn(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<TokenRes>> refreshToken(@RequestBody RefreshReq req) {
        return BaseResponse.of(authUseCase.refresh(req));
    }


}
