package com.learnmore.legacy.domain.daily.service;

import com.learnmore.legacy.domain.daily.model.Daily;
import com.learnmore.legacy.domain.daily.model.repo.DailyJpaRepo;
import com.learnmore.legacy.domain.daily.presentation.dto.req.DailyReq;
import com.learnmore.legacy.domain.daily.presentation.dto.res.DailyRes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyService {
    private final DailyJpaRepo dailyJpaRepo;

    public List<DailyRes> getAllDaily() {
        return dailyJpaRepo.findAll()
                .stream()
                .map(DailyRes::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public DailyRes addDaily(DailyReq dailyReq) {
        Daily daily = Daily.builder()
                .title(dailyReq.getTitle())
                .content(dailyReq.getContent())
                .build();
        dailyJpaRepo.save(daily);
        return DailyRes.from(daily);
    }

    public DailyRes patchDaily(Long id, DailyReq dailyReq) {
        Daily daily = dailyJpaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디를 찾을 없습니다."));
        daily.update(dailyReq);
        dailyJpaRepo.save(daily);
        return DailyRes.from(daily);
    }

    public void deleteDaily(Long id) {
        dailyJpaRepo.deleteById(id);
    }

    public DailyRes getDailyById(Long id) {
        Daily daily = dailyJpaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("아이디를 찾을 수 없습니다."));
        return DailyRes.from(daily);
    }
}
