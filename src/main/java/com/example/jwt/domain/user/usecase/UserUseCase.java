package com.example.jwt.domain.user.usecase;

import com.example.jwt.domain.user.presentation.dto.UserRes;
import com.example.jwt.global.common.repo.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {
    private final UserSessionHolder userSessionHolder;

    public UserRes getMe(){
        return UserRes.from(userSessionHolder.get());
    }
}
