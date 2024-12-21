package com.green.project_1.schedule.model.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

//일정생성 Request DTO
@Getter
@ToString
@Schema(title="일정 입력")
@Validated
public class ScheduleAddReq {
    @Positive
    @Schema(name="로그인한 유저PK",description = "로그인한 팀원 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long signedUserNo;
    @Positive
    @Schema(name="일정실행 유저PK",description = "일정 실행하는 팀원 번호", type="long", example="1",requiredMode=Schema.RequiredMode.REQUIRED)
    private long scheduleUserNo;
    @Positive
    @Schema(name="프로젝트PK",description = "일정 진행하는 프로젝트 번호", type="long", example="2",requiredMode=Schema.RequiredMode.REQUIRED)
    private long projectNo;
    @Schema(name="일정제목",description = "일정제목", type="정수", example="주간회의록 작성하기",requiredMode=Schema.RequiredMode.REQUIRED)
    private String content;
    @Schema(name="일정상세",description = "일정상세", type="정수", example="김모씨白 왱알왱알, 그렇게하기로 협의")
    private String detail;

    @JsonIgnore
    private long scheduleNo;
}
