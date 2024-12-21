package com.green.project_1.schedule;


import com.green.project_1.common.ResponseResult;
import com.green.project_1.schedule.model.req.DeleteSchedule;
import com.green.project_1.schedule.model.req.ScheduleAddReq;
import com.green.project_1.schedule.model.req.SchedulePatch;
import com.green.project_1.schedule.model.res.MemberSchedule;
import com.green.project_1.schedule.model.res.ScheduleAddRes;
import com.green.project_1.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleMapper mapper;
    private final UserMapper userMapper;
    //일정 상세
    public ResponseResult scheduleDetail(long scheduleNo){
        if(scheduleNo <= 0){
            return ResponseResult.serverError();
        }
        MemberSchedule detail=mapper.scheduleDetail(scheduleNo);
        MemberSchedule res=(MemberSchedule)ResponseResult.success();
        res.setChecked(detail.isChecked());
        res.setDetail(detail.getDetail());
        res.setTitle(detail.getTitle());
        res.setCreatedAt(detail.getCreatedAt());
        res.setProjectName(detail.getProjectName());
        res.setUserNickname(detail.getUserNickname());
        res.setUserProfilePic(detail.getUserProfilePic());
        return res;
    }

    //일정 생성
    public ResponseResult scheduleAdd(ScheduleAddReq sch){
        long leaderNo= userMapper.leaderNo(sch.getProjectNo());
        long doUserNo=sch.getScheduleUserNo();
        long myUserNo=sch.getSignedUserNo();
        if(myUserNo!=leaderNo && myUserNo!=doUserNo){
            return ResponseResult.noPermission();
        }
        int result=mapper.scheduleAdd(sch);
        if(result==0){ResponseResult.serverError();}

        ScheduleAddRes res=(ScheduleAddRes)ResponseResult.success();
        res.setScheduleNo(sch.getScheduleNo());
        return res;
    }

    //일정 완료 체크
    public ResponseResult scheduleComplete(long signedUserNo, long scheduleNo){
        if(scheduleNo <= 0||signedUserNo<=0){
            return ResponseResult.serverError();
        }
        long doUserNo=userMapper.scheduleUserNoFromSchedule(scheduleNo);
        if(doUserNo==signedUserNo){
            return ResponseResult.noPermission();
        }
        mapper.scheduleComplete(scheduleNo, mapper.getCheked(scheduleNo));

        return ResponseResult.success();
    }

    //일정 수정
    //팀원만 수정하는건 xml파일에서 mybatis로 처리
    public ResponseResult scheduleUpdate(SchedulePatch patch){
        long leaderNo= userMapper.leaderNo(patch.getProjectNo());
        long doUserNo= patch.getScheduleUserNo();
        long myUserNo=patch.getSignedUserNo();
        if(myUserNo!=leaderNo && myUserNo!=doUserNo){
            return ResponseResult.noPermission();
        }
        int res=mapper.scheduleUpdate(patch);
        if(res==0){return ResponseResult.serverError();}
        return ResponseResult.success();
    }

    //일정 삭제
    public ResponseResult scheduleDelete(DeleteSchedule del){
        long doUserNo=del.getScheduleUserNo();
        long leaderNo=userMapper.leaderNo(del.getProjectNo());
        long myUserNo=del.getSignedUserNo();
        if(doUserNo!=myUserNo && leaderNo!=myUserNo){
            return ResponseResult.noPermission();
        }
        int res=mapper.scheduleDelete(del);
        if(res==0){return ResponseResult.serverError();}
        return ResponseResult.success();
    }
}
