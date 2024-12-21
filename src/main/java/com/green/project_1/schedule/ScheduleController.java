package com.green.project_1.schedule;

import com.green.project_1.common.ResponseResult;
import com.green.project_1.schedule.model.req.DeleteSchedule;
import com.green.project_1.schedule.model.req.ScheduleAddReq;
import com.green.project_1.schedule.model.req.SchedulePatch;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("project/schedule")
@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    @PostMapping
    @Operation(summary="일정생성", description = "일정 생성하기")
    public ResponseResult scheduleAdd(@Valid @RequestBody ScheduleAddReq Schedule) {
        return service.scheduleAdd(Schedule);
    }

    @PostMapping("{scheduleNo}")
    @Operation(summary = "일정 완료 체크", description = "일정 완료체크 토글")
    public ResponseResult scheduleComplete(@RequestParam long signedUserNo,
                                            @RequestParam long scheduleNo) {
        return service.scheduleComplete(signedUserNo,scheduleNo);
    }

    @GetMapping("{scheduleNo}")
    @Operation(summary = "일정상세페이지", description = "특정 일정 상세 페이지 가져오기")
    public ResponseResult scheduleDetail(@ParameterObject long scheduleNo){
        return service.scheduleDetail(scheduleNo);
    }

    @PutMapping("{scheduleNo}")
    @Operation(summary="일정 수정하기", description = "content와 detail을 빼고 보내면 일정 실행하는 유저만 변경됩니다.")
    public ResponseResult scheduleUpdate(@Valid @RequestBody SchedulePatch patch){
        return service.scheduleUpdate(patch);
    }

    @DeleteMapping("{scheduleNo}")
    @Operation(summary = "일정삭제하기", description = "일정 삭제하기")
    public ResponseResult scheduleDelete(@Valid @ParameterObject DeleteSchedule delSchedule){
        return service.scheduleDelete(delSchedule);
    }

}
