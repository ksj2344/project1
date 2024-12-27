package com.green.project_1.project;

import com.green.project_1.common.ResponseResult;
import com.green.project_1.project.model.req.ProjectUserEdit;
import com.green.project_1.project.model.req.ProjectUserLockReq;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("project")
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService service;

    @PatchMapping
    @Operation(summary = "유저 잠금", description = "유저 잠금설정")
    public ResponseResult userLock(@Valid @RequestBody ProjectUserLockReq p){
        return service.userLock(p);
    }

    @PostMapping("search-user")
    @Operation(summary = "프로젝트 일원 수정", description = "프로젝트 인원 추가 및 삭제")
    public ResponseResult editUserList(@Valid @RequestBody ProjectUserEdit p){
        return service.editUserList(p);
    }

    @PostMapping("{projectNo}")
    @Operation(summary = "프로젝트 완료처리", description = "스케줄 미완료 없을 때 프로젝트 완료 처리")
    public ResponseResult projectComplete(@PathVariable @RequestParam long projectNo, @RequestParam long signedUserNo){
        return service.projectComplete(projectNo,signedUserNo);
    }
}
