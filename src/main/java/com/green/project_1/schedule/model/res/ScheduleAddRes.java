package com.green.project_1.schedule.model.res;

import com.green.project_1.common.ResponseResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ScheduleAddRes extends ResponseResult {
    private long scheduleNo;

    public ScheduleAddRes(String code,long scheduleNo) {
        super(code);
        this.scheduleNo = scheduleNo;
    }
}
