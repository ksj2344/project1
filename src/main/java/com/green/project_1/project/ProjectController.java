package com.green.project_1.project;

import com.green.project_1.common.ResponseResult;
import com.green.project_1.project.model.req.ProjectUserLockReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService service;

    @PatchMapping
    public ResponseResult userLock(@Valid @RequestBody ProjectUserLockReq p){
        return service.userLock(p);
    }
}
