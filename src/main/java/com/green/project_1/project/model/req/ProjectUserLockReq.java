package com.green.project_1.project.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Schema(title="유저 잠금")
@Validated
public class ProjectUserLockReq {
    @Positive
    @Schema(description = "로그인한 유저", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long signedUserNo;
    @Positive
    @Schema(description = "잠글 유저", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long targetUserNo;
    @Positive
    @Schema(description = "프로젝트 번호", type="long", example="4",requiredMode=Schema.RequiredMode.REQUIRED)
    private long projectNo;
}
