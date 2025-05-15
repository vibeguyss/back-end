package com.example.jwt.domain.user.presentation.dto;

import com.example.jwt.domain.user.model.User;

public record UserRes(
        Long id
) {
    public static UserRes from(User user) {
        return new UserRes(user.getUserId());
    }
}


