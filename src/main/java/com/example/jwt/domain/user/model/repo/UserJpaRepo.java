package com.example.jwt.domain.user.model.repo;

import com.example.jwt.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, Long> {
    User findByUserId(Long id);


}
