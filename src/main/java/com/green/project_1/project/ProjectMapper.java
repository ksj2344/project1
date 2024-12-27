package com.green.project_1.project;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int userLock(long targetUserNo);

    int delUserProjectList(long projectNo, List<Long> deleteUserNoList);
    int insUserProjectList(long projectNo, List<Long> insertUserNoList);

    int projectComplete(long projectNo);

    void checkDeadline();
}
