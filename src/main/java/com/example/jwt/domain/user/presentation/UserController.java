package com.example.jwt.domain.user.presentation;

import com.example.jwt.domain.user.presentation.dto.UserRes;
import com.example.jwt.domain.user.usecase.UserUseCase;
import com.example.jwt.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<UserRes>> getMe(){//todo 뭐뭐 필요한지 확인
        return BaseResponse.of(userUseCase.getMe());
    }
}
