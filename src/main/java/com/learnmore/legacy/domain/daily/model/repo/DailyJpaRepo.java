package com.learnmore.legacy.domain.daily.model.repo;

import com.learnmore.legacy.domain.daily.model.Daily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyJpaRepo extends JpaRepository<Daily, Long> {
}
