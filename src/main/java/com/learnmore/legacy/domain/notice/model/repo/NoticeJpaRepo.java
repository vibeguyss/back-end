package com.learnmore.legacy.domain.notice.model.repo;

import com.learnmore.legacy.domain.notice.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeJpaRepo extends JpaRepository<Notice, Long> {
}
