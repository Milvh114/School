package com.group1.studentprojectportal.controller;

import com.group1.studentprojectportal.constant.AppContants;
import com.group1.studentprojectportal.payload.ClassDto;
import com.group1.studentprojectportal.payload.PagedResponse;
import com.group1.studentprojectportal.payload.UserRequest;
import com.group1.studentprojectportal.payload.UserResponse;
import com.group1.studentprojectportal.service.impl.ClassService;
import com.group1.studentprojectportal.service.impl.UserService;
import com.group1.studentprojectportal.util.PaginationChecking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@Slf4j
public class ClassController {
    private final ClassService classService;
    private final UserService userService;

    public ClassController(ClassService classService, UserService userService) {
        this.classService = classService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ClassDto>>  getClasses(
            @RequestParam(name = "page", required = false,
                    defaultValue = AppContants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false,
                    defaultValue = AppContants.DEFAULT_PAGE_SIZE) Integer size
    ) {
        log.info("all class");
        return classService.getAllClasses(page, size);
    }

    @PostMapping
    public ResponseEntity<ClassDto> addClass(@RequestBody ClassDto request) {
        log.info("add class");
        return classService.addClass(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDto> getClassById(@PathVariable Integer id) {
        log.info("id class");
        return classService.getClassById(id);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<PagedResponse<UserResponse>> getUsersWithinClass(
            @PathVariable Integer id,
            @RequestParam(name = "page", required = false, defaultValue = AppContants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppContants.DEFAULT_PAGE_SIZE) Integer size
    ) {
        try {
            PaginationChecking.validatePageNumberAndSize(page, size);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("class users");
        return userService.getStudentsWithinClass(id, page, size);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDto> updateClass(
            @PathVariable Integer id,
            @RequestBody ClassDto request
    ) {
        log.info("update class");
        return classService
                .updateClass(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PagedResponse<ClassDto>>  deleteClass(@PathVariable Integer id) {
        log.info("delete class");
        return classService.deleteClassById(id);
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<ClassDto> updateClassStatus(@PathVariable Integer id) {
        log.info("update class status");
        return classService.updateStatus(id);
    }

    @PostMapping("{id}/users")
    public ResponseEntity<ClassDto> addStudentToClass(
            @RequestParam(name = "action", required = false) String action,
            @PathVariable Integer id,
            @RequestBody List<UserRequest> studentList
            ) {
        if (action.equalsIgnoreCase("add")){
            return classService.addStudentToClass(id, studentList);
        }
        else{
            return classService.removeStudentFromClass(id, studentList);
        }
    }
}
