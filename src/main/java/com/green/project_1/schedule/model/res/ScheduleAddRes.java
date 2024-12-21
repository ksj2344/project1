package com.green.project_1.schedule.model.res;

import com.green.project_1.common.ResponseResult;
import lombok.Setter;

@Setter
public class ScheduleAddRes extends ResponseResult {
    private long scheduleNo;

    public ScheduleAddRes(String code) {
        super(code);
    }
}
