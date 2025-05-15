package com.example.jwt.global.common.repo;

import com.example.jwt.domain.user.model.User;
import com.example.jwt.domain.user.model.repo.UserJpaRepo;
import com.example.jwt.domain.user.error.UserError;
import com.example.jwt.domain.user.service.UserService;
import com.example.jwt.global.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class UserSessionHolder {
    private final UserService userService;

    public User get() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findById(Long.valueOf(userId));
    }
}
