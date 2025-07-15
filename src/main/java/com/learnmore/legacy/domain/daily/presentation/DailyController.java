package com.learnmore.legacy.domain.daily.presentation;

import com.learnmore.legacy.domain.daily.presentation.dto.req.DailyReq;
import com.learnmore.legacy.domain.daily.presentation.dto.res.DailyRes;
import com.learnmore.legacy.domain.daily.service.DailyService;
import com.learnmore.legacy.global.common.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/daily")
public class DailyController {
    private final DailyService dailyService;

    @Operation(summary = "일기 모두 조회", description = "일기를 모두 조회합니다.")
    @GetMapping("")
    public ResponseEntity<BaseResponse<List<DailyRes>>> getAllDaily() {
        return BaseResponse.of(dailyService.getAllDaily());
    }

    @Operation(summary = "일기 모두 조회", description = "일기를 모두 조회합니다.")
    @PostMapping("")
    public ResponseEntity<BaseResponse<DailyRes>> addDaily(@RequestBody DailyReq daily) {
        return BaseResponse.of(dailyService.addDaily(daily));
    }

    @Operation(summary = "일기 수정", description = "일기 수정합니다.")
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<DailyRes>> updateDaily(@PathVariable Long id, @RequestBody DailyReq daily) {
        return BaseResponse.of(dailyService.patchDaily(id, daily));
    }

    @Operation(summary = "일기 삭제", description = "일기를 삭제합니다.")
    @DeleteMapping("/{id}")
    public void deleteDaily(@PathVariable Long id) {
        dailyService.deleteDaily(id);
    }

    @Operation(summary = "일기 상세 조회", description = "일기를 상세 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<DailyRes>> getDaily(@PathVariable Long id) {
        return BaseResponse.of(dailyService.getDailyById(id));
    }

}
