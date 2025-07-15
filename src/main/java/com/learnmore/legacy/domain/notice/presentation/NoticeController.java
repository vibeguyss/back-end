package com.learnmore.legacy.domain.notice.presentation;

import com.learnmore.legacy.domain.notice.presentation.dto.req.NoticeReq;
import com.learnmore.legacy.domain.notice.presentation.dto.res.NoticeRes;
import com.learnmore.legacy.domain.notice.service.NoticeService;
import com.learnmore.legacy.global.common.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @Operation(summary = "공지사항 모두 조회", description = "공지사항을 모두 조회합니다.")
    @GetMapping("")
    public ResponseEntity<BaseResponse<List<NoticeRes>>> getAllNotice() {
        return BaseResponse.of(noticeService.getAllNotice());
    }

    @Operation(summary = "일기 모두 조회", description = "일기를 모두 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<NoticeRes>> getNoticeById(@PathVariable Long id) {
        return BaseResponse.of(noticeService.getNoticeById(id));
    }

    @Operation(summary = "일기 모두 조회", description = "일기를 모두 조회합니다.")
    @PostMapping("")
    public ResponseEntity<BaseResponse<NoticeRes>> addNotice(@RequestBody NoticeReq notice) {
        return BaseResponse.of(noticeService.addNotice(notice));
    }

    @Operation(summary = "일기 모두 조회", description = "일기를 모두 조회합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<NoticeRes>> updateNotice(@PathVariable Long id, @RequestBody NoticeReq notice) {
        return BaseResponse.of(noticeService.patchNotice(id, notice));
    }

    @Operation(summary = "일기 모두 조회", description = "일기를 모두 조회합니다.")
    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
    }
}
