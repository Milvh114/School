package com.group1.studentprojectportal.service;

import com.group1.studentprojectportal.constant.SystemSettings;
import com.group1.studentprojectportal.payload.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProjectService {

    ResponseEntity<ProjectDto> addProject(ProjectDto projectDto);
    ResponseEntity<ProjectDto>  updateProject(Integer id, ProjectDto request);
    ResponseEntity<ProjectDto> deleteProjectById(Integer id);
    ResponseEntity<PagedResponse<ProjectDto>> getAllProjects(Integer page, Integer size);

    ResponseEntity<PagedResponse<ProjectDto>> getAllProjectsByClass(Integer page, Integer size, Integer ClassId);

    ResponseEntity<List<ProjectDto>> getAllProjectsByClass(Integer ClassId);

    ResponseEntity<ProjectDto> getProjectById(Integer id);

//    ResponseEntity<ClassDto> addStudentToProject(Integer id, List<UserRequest> studentList);

//    ResponseEntity<ProjectDto> updateProjectLead(Integer id, UserRequest lead);
}
