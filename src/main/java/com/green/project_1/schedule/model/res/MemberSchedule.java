package com.green.project_1.schedule.model.res;

import com.green.project_1.common.ResponseResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class MemberSchedule extends ResponseResult {
    private String title;
    private String detail;
    private boolean checked;
    private String createdAt;
    private String projectName;
    private String userNickname;
    private String userProfilePic;


    public MemberSchedule(String code) {
        super(code);
    }
}
