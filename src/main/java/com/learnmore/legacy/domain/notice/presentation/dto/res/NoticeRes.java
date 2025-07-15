package com.learnmore.legacy.domain.notice.presentation.dto.res;

import com.learnmore.legacy.domain.notice.model.Notice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeRes {
    private Long noticeId;
    private String title;
    private String content;
    private String name;
    private LocalDateTime createdAt;


    public static NoticeRes from(Notice notice) {
        return NoticeRes.builder()
                .noticeId(notice.getNoticeId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .name(notice.getName())
                .createdAt(notice.getCreateAt())
                .build();
    }
}
