package com.learnmore.legacy.domain.notice.service;

import com.learnmore.legacy.domain.notice.model.Notice;
import com.learnmore.legacy.domain.notice.model.repo.NoticeJpaRepo;
import com.learnmore.legacy.domain.notice.presentation.dto.req.NoticeReq;
import com.learnmore.legacy.domain.notice.presentation.dto.res.NoticeRes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeJpaRepo noticeJpaRepo;

    public List<NoticeRes> getAllNotice() {
        return noticeJpaRepo.findAll()
                .stream()
                .map(NoticeRes::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public NoticeRes addNotice(NoticeReq noticeReq) {
        Notice notice = Notice.builder()
                .title(noticeReq.getTitle())
                .content(noticeReq.getContent())
                .build();
        noticeJpaRepo.save(notice);
        return NoticeRes.from(notice);
    }

    public NoticeRes patchNotice(Long id, NoticeReq noticeReq) {
        Notice notice = noticeJpaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디를 찾을 없습니다."));
        notice.update(noticeReq);
        noticeJpaRepo.save(notice);
        return NoticeRes.from(notice);
    }

    public void deleteNotice(Long id) {
        noticeJpaRepo.deleteById(id);
    }

    public NoticeRes getNoticeById(Long id) {
        Notice notice = noticeJpaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디를 찾을 수 없습니다."));
        return NoticeRes.from(notice);
    }

}
