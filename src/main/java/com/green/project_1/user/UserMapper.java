package com.green.project_1.user;

import com.green.project_1.schedule.model.GetLeaderNoAndScheduledNoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //프로젝트 번호로 리더 번호 가져오기
    long leaderNo(long projectNo);
    //스케줄 번호로 리더 번호 가져오기
    GetLeaderNoAndScheduledNoDto scheduledAndLeaderNoFromscheduleNo(long scheduleNo);
}