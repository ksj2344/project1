package com.green.project_1.project;

import com.green.project_1.project.model.req.ProjectUserLockReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int userLock(ProjectUserLockReq p);

    int delUserProjectList(long projectNo, List<Long> deleteUserNoList);
    int insUserProjectList(long projectNo, List<Long> insertUserNoList);
}
