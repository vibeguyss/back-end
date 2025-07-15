package com.learnmore.legacy.domain.notice.presentation.dto.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeReq {
    private String title;
    private String content;
}
