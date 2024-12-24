package com.green.project_1.project;

import com.green.project_1.project.model.req.ProjectUserLockReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    int userLock(ProjectUserLockReq p);
}
