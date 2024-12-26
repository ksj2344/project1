package com.green.project_1.project;

import com.green.project_1.common.ResponseCode;
import com.green.project_1.common.ResponseResult;
import com.green.project_1.project.model.req.ProjectUserEdit;
import com.green.project_1.project.model.req.ProjectUserLockReq;
import com.green.project_1.project.model.req.ProjectUserUnLockReq;
import com.green.project_1.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectMapper mapper;
    private final UserMapper userMapper;

    ResponseResult userLock(ProjectUserLockReq p){
        if(userMapper.leaderNo(p.getProjectNo())!=p.getSignedUserNo()){
            ResponseResult.noPermission();
        }
        mapper.userLock(p.getTargetUserNo());
        return ResponseResult.success();
    }

    ResponseResult userUnLock(ProjectUserUnLockReq p){
        if(userMapper.leaderNo(p.getProjectNo())!=p.getSignedUserNo()){
            ResponseResult.noPermission();
        }
        mapper.userUnLock(p.getTargetUserNo());
        return ResponseResult.success();
    }

    ResponseResult userLockToggle(ProjectUserLockReq p){
        if(userMapper.leaderNo(p.getProjectNo())!=p.getSignedUserNo()){
            ResponseResult.noPermission();
        }
        mapper.userLockToggle(p.getTargetUserNo(), mapper.checkLocked(p.getProjectNo(),p.getTargetUserNo()));
        return ResponseResult.success();
    }

    ResponseResult editUserList(ProjectUserEdit p){
        long projectNo = p.getProjectNo();
        if(p.getSignedUserNo()!=userMapper.leaderNo(projectNo)){
            ResponseResult.noPermission();
        }
        List<Long> insUserList = p.getInsertUserNoList()!=null?p.getInsertUserNoList():new ArrayList<>();
        List<Long> delUserList = p.getDeleteUserNoList()!=null?p.getDeleteUserNoList():new ArrayList<>();
        if(insUserList.size()!=0){
            int ins= mapper.insUserProjectList(projectNo,insUserList);
            if(ins==0){ResponseResult.badRequest(ResponseCode.DATABASE_ERROR);}
        }
        if(delUserList.size()!=0){
            int del= mapper.delUserProjectList(projectNo,delUserList);
            if(del==0){ResponseResult.badRequest(ResponseCode.DATABASE_ERROR);}
        }
        return ResponseResult.success();
    }
}
