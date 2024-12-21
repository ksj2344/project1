package com.green.project_1.schedule.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

//일정삭제 Request DTO
@Getter
@Validated
public class DeleteSchedule {
    @Positive
    @Schema(name="프로젝트PK",description = "일정 진행하는 프로젝트 번호", type="long", example="2",requiredMode=Schema.RequiredMode.REQUIRED)
    private long projectNo;
    @Positive
    @Schema(name="일정PK",description = "일정 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long scheduleNo;
    @Positive
    @Schema(name="일정실행 유저PK",description = "일정 실행하는 팀원 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long scheduleUserNo;
    @Positive
    @Schema(name="로그인한 유저PK",description = "로그인한 팀원 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long signedUserNo;
}
