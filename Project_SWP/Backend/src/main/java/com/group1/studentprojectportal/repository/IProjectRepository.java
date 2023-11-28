package com.group1.studentprojectportal.repository;

import com.group1.studentprojectportal.entity.Project;
import com.group1.studentprojectportal.payload.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Integer> {

    Page<Project> findByProjectClassId(Integer id, Pageable pageable);
//    List<UserResponse> findAllByMembersNot
    List<Project> findByProjectClassId(Integer id);
    Project findProjectById(Integer id);





}