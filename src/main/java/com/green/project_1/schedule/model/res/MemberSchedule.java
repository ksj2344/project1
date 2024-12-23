package com.green.project_1.schedule.model.res;

import com.green.project_1.common.ResponseResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSchedule extends ResponseResult {
    private String content;
    private String detail;
    private boolean checked;
    private String createdAt;
    private String userNickname;
    private String userProfilePic;


    public MemberSchedule(String code, String content, String detail, boolean checked, String createdAt,
                          String userNickname, String userProfilePic) {
        super(code);
        this.content = content;
        this.detail = detail;
        this.checked = checked;
        this.createdAt = createdAt;
        this.userNickname = userNickname;
        this.userProfilePic = userProfilePic;
    }
}
