package com.example.jwt.domain.user.model.repo;

import com.example.jwt.domain.user.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageJpaRepo extends JpaRepository<ProfileImage, Long> {
}
