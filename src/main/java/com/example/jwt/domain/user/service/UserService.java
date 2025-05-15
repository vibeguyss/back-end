package com.example.jwt.domain.user.service;

import com.example.jwt.domain.user.model.User;
import com.example.jwt.domain.user.model.repo.UserJpaRepo;
import com.example.jwt.domain.user.error.UserError;
import com.example.jwt.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepo userJpaRepo;

    public User findById(Long userId) {
        return userJpaRepo.findById(userId)
                .orElseThrow(() -> new CustomException(UserError.USER_NOT_FOUND));
    }

    public void save(User user) {
        userJpaRepo.save(user);
    }

    public boolean existsById(Long userId) {
        return userJpaRepo.existsById(userId);
    }

}
