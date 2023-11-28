package com.group1.studentprojectportal.controller;

import com.group1.studentprojectportal.constant.AppContants;
import com.group1.studentprojectportal.payload.*;
import com.group1.studentprojectportal.service.IProjectService;
import com.group1.studentprojectportal.util.PaginationChecking;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Slf4j
public class ProjectController {

    @Autowired
    private IProjectService projectService;


    @GetMapping()
    public  ResponseEntity<PagedResponse<ProjectDto>> getProjects(
            @RequestParam(name = "page", required = false, defaultValue = AppContants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppContants.DEFAULT_PAGE_SIZE) Integer size
    ){
        try {
            PaginationChecking.validatePageNumberAndSize(page, size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return projectService.getAllProjects(page, size);
    }

    @GetMapping("/class")
    public  ResponseEntity<PagedResponse<ProjectDto>> getProjectsByClass(
            @RequestParam(name = "page", required = false, defaultValue = AppContants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppContants.DEFAULT_PAGE_SIZE) Integer size,
            @RequestParam(name = "classID",required = false, defaultValue = "") Integer classId
    ){
        try {
            PaginationChecking.validatePageNumberAndSize(page, size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return projectService.getAllProjectsByClass(page, size, classId);
    }

    @GetMapping("/class/all")
    public  ResponseEntity<List<ProjectDto>> getProjectsByClass(
            @RequestParam(name = "classID", defaultValue = "") Integer classId
    ){

        return projectService.getAllProjectsByClass(classId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Integer id) {
        return projectService.getProjectById(id);
    }

    @PostMapping()
    public ResponseEntity<ProjectDto> addProject(
            @RequestBody @Valid ProjectDto request) {
        return projectService.addProject(request);
    }


//    @PostMapping("/{id}/lead")
//    public ResponseEntity<ProjectDto> updateProjectLead(@PathVariable Integer id,
//    @RequestBody UserRequest lead) {
//        log.info("update project status");
//        return projectService.updateLead(id, lead);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateSystemSetting(@PathVariable Integer id, @RequestBody ProjectDto request) {
        return projectService.updateProject(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDto> deleteProject(@PathVariable Integer id) {
        return projectService.deleteProjectById(id);
    }



}
