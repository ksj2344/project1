package com.green.project_1.project;

import com.green.project_1.common.ResponseCode;
import com.green.project_1.common.ResponseResult;
import com.green.project_1.project.model.req.ProjectUserEdit;
import com.green.project_1.project.model.req.ProjectUserLockReq;
import com.green.project_1.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectMapper mapper;
    private final UserMapper userMapper;

    ResponseResult userLock(ProjectUserLockReq p){
        if(userMapper.leaderNo(p.getProjectNo())!=p.getSignedUserNo()){
            return ResponseResult.noPermission();
        }
        mapper.userLock(p.getTargetUserNo());
        return ResponseResult.success();
    }

    ResponseResult editUserList(ProjectUserEdit p){
        long projectNo = p.getProjectNo();
        if(p.getSignedUserNo()!=userMapper.leaderNo(projectNo)){
            return ResponseResult.noPermission();
        }
        List<Long> insUserList = p.getInsertUserNoList()!=null?p.getInsertUserNoList():new ArrayList<>();
        List<Long> delUserList = p.getDeleteUserNoList()!=null?p.getDeleteUserNoList():new ArrayList<>();
        if(!delUserList.isEmpty()){
            int ins= mapper.insUserProjectList(projectNo,insUserList);
            if(ins==0){ResponseResult.badRequest(ResponseCode.DATABASE_ERROR);}
        }
        if(!delUserList.isEmpty()){
            int del= mapper.delUserProjectList(projectNo,delUserList);
            if(del==0){ResponseResult.badRequest(ResponseCode.DATABASE_ERROR);}
        }
        return ResponseResult.success();
    }

    ResponseResult projectComplete(long projectNo, long signedUserNo){
        if(signedUserNo!=userMapper.leaderNo(projectNo)){
           return ResponseResult.noPermission();
        }
        int res=mapper.projectComplete(projectNo);
        if(res==0){return ResponseResult.badRequest(ResponseCode.FAIL);}
        return ResponseResult.success();
    }
}
