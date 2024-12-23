package com.green.project_1.schedule;

import com.green.project_1.schedule.model.req.DeleteSchedule;
import com.green.project_1.schedule.model.req.ScheduleAddReq;
import com.green.project_1.schedule.model.req.SchedulePatch;
import com.green.project_1.schedule.model.res.MemberSchedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {
    //일정 상세 페이지 불러오기
    MemberSchedule scheduleDetail(long scheduleNo);
    //일정 생성
    int scheduleAdd(ScheduleAddReq t);

    //일정 완료체크 확인
    int scheduleComplete(long scheduleNo, int cheked);
    //완료 여부 가져오기
    int getCheked(long scheduleNo);

    //일정 수정
    int scheduleUpdate(SchedulePatch p);
    //일정 삭제
    int scheduleDelete(long scheduleNo);
}
