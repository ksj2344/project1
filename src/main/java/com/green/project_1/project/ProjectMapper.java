package com.green.project_1.project;

import com.green.project_1.project.model.req.ProjectUserLockReq;
import com.green.project_1.project.model.req.ProjectUserUnLockReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int userLock(long targetUserNo);
    int userUnLock(long targetUserNo);
    int userLockToggle(long targetUserNo, int locked);
    int checkLocked(long projectNo, long targetUserNo);

    int delUserProjectList(long projectNo, List<Long> deleteUserNoList);
    int insUserProjectList(long projectNo, List<Long> insertUserNoList);
}
