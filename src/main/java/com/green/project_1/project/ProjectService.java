package com.green.project_1.project;

import com.green.project_1.common.ResponseResult;
import com.green.project_1.project.model.req.ProjectUserLockReq;
import com.green.project_1.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectMapper mapper;
    private final UserMapper userMapper;

    ResponseResult userLock(ProjectUserLockReq p){
        if(userMapper.leaderNo(p.getProjectNo())!=p.getSignedUserNo()){
            ResponseResult.noPermission();
        }
        mapper.userLock(p);
        return ResponseResult.success();
    }

}
