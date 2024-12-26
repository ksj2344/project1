package com.green.project_1.project;

import com.green.project_1.common.ResponseResult;
import com.green.project_1.project.model.req.ProjectUserEdit;
import com.green.project_1.project.model.req.ProjectUserLockReq;
import com.green.project_1.project.model.req.ProjectUserUnLockReq;
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

    @PatchMapping("unlock")
    @Operation(summary = "유저 해제", description = "유저 잠금설정")
    public ResponseResult userUnLock(@Valid @RequestBody ProjectUserUnLockReq p){
        return service.userUnLock(p);
    }

    @PatchMapping("locktoggle")
    @Operation(summary = "유저 토글", description = "유저 잠금설정")
    public ResponseResult userLockTogle(@Valid @RequestBody ProjectUserLockReq p){
        return service.userLockToggle(p);
    }

    @PostMapping("search-user")
    @Operation(summary = "프로젝트 일원 수정", description = "프로젝트 인원 추가 및 삭제")
    public ResponseResult editUserList(@Valid @RequestBody ProjectUserEdit p){
        return service.editUserList(p);
    }
}
