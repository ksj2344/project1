package com.green.project_1.schedule;


import com.green.project_1.common.ResponseCode;
import com.green.project_1.common.ResponseResult;
import com.green.project_1.schedule.model.req.DeleteSchedule;
import com.green.project_1.schedule.model.req.ScheduleAddReq;
import com.green.project_1.schedule.model.req.SchedulePatch;
import com.green.project_1.schedule.model.res.ScheduleDetail;
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
    public ResponseResult scheduleDetail(long scheduleNo, long signedUserNo){
        if(scheduleNo <= 0&&signedUserNo <= 0){
            return ResponseResult.serverError();
        }
        ScheduleDetail<Integer> detail=mapper.scheduleDetail(scheduleNo,signedUserNo);
        return new ScheduleDetail<Boolean>("OK",
                detail.getContent(),
                detail.getDetail(),
                detail.isChecked(),
                detail.getCreatedAt(),
                detail.getUserNickname(),
                detail.getUserProfilePic()
                ,detail.getMySchedule()==1?true:false
                );
    }

    //일정 생성
    public ResponseResult scheduleAdd(ScheduleAddReq sch){
        log.info("service>Schedule:{}", sch);
        long leaderNo= userMapper.leaderNo(sch.getProjectNo());
        long myUserNo=sch.getSighInUserNo();
        if(myUserNo!=leaderNo && myUserNo!=sch.getScheduleUserNo()){
            return ResponseResult.noPermission();
        }
        int result=mapper.scheduleAdd(sch);
        if(result==0){ResponseResult.serverError();}
        return new ScheduleAddRes("OK",sch.getScheduleNo());
    }

    //일정 완료 체크
    public ResponseResult scheduleComplete(long signedUserNo, long scheduleNo){
        if(scheduleNo <= 0||signedUserNo<=0){
            return ResponseResult.badRequest(ResponseCode.FAIL);
        }
        long doUserNo=userMapper.scheduleUserNoFromSchedule(scheduleNo);
        if(doUserNo!=signedUserNo){
            return ResponseResult.noPermission();
        }
        mapper.scheduleComplete(scheduleNo, mapper.getCheked(scheduleNo));

        return ResponseResult.success();
    }

    //일정 수정
    //실행하는 팀원만 수정하는건 xml파일에서 mybatis로 처리
    public ResponseResult scheduleUpdate(SchedulePatch patch){
        long leaderNo= userMapper.leaderNo(patch.getProjectNo());
        long doUserNo= patch.getScheduleUserNo();
        long myUserNo=patch.getSignedUserNo();
        if(myUserNo!=leaderNo && myUserNo!=doUserNo){
            return ResponseResult.noPermission();
        }
        String content=patch.getContent();
        String detail=patch.getDetail();
        if(content!=null&&detail==null||content==null&&detail!=null){
            return ResponseResult.badRequest(ResponseCode.NOT_NULL);
        }
        int res=mapper.scheduleUpdate(patch);
        if(res==0){return ResponseResult.badRequest(ResponseCode.FAIL);}
        return ResponseResult.success();
    }

    //일정 삭제
    public ResponseResult scheduleDelete(DeleteSchedule del){
        long doUserNo=userMapper.scheduleUserNoFromSchedule(del.getScheduleNo());
        if(doUserNo!=del.getSignedUserNo()){
            return ResponseResult.noPermission();
        }
        int res=mapper.scheduleDelete(del.getScheduleNo());
        if(res==0){return ResponseResult.serverError();}
        return ResponseResult.success();
    }
}
