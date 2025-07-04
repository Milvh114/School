package com.group1.studentprojectportal.service.impl;

import com.group1.studentprojectportal.entity.*;
import com.group1.studentprojectportal.payload.PagedResponse;
import com.group1.studentprojectportal.payload.SystemSettingDto;
import com.group1.studentprojectportal.payload.dto.ClassDto;
import com.group1.studentprojectportal.payload.ProjectDto;
import com.group1.studentprojectportal.payload.UserDto;
import com.group1.studentprojectportal.repository.IClassRepository;
import com.group1.studentprojectportal.repository.IProjectRepository;
import com.group1.studentprojectportal.repository.IUserRepository;
import com.group1.studentprojectportal.service.IProjectService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IProjectService {

    private final static Logger log = LoggerFactory.getLogger(SubjectService.class);
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private  IProjectRepository projectRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IClassRepository classRepository;

    @Override
    public ResponseEntity<ProjectDto> addProject(ProjectDto projectDto) {
        Project project = dtoToEntity(projectDto);
        System.out.println(project);
        Project saved = projectRepository.save(project);
        ProjectDto resPayload = entityToDto(saved);
        return new ResponseEntity<>(resPayload, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProjectDto> updateProject(Integer id, ProjectDto request) {
        Project projectOld = projectRepository.findProjectById(id);
        if (projectOld == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//      update subject info
        Project project = dtoToEntity(request, projectOld);
        System.out.println("ok3");
        System.out.println(project);
        projectRepository.save(project);
        return new ResponseEntity<>(entityToDto(project), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDto> deleteProjectById(Integer id) {
        Project project = projectRepository.findProjectById(id);
        System.out.println(project);
        ProjectDto projectDto = entityToDto(project);
        System.out.println(projectDto);
        projectRepository.deleteById(id);
        if (!projectRepository.existsById(id)) {
            return new ResponseEntity<>(
                    projectDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<PagedResponse<ProjectDto>> getAllProjects(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        Page<Project> projectPage = projectRepository.findAll(pageable);
        if (projectPage.getNumberOfElements() == 0) {
            PagedResponse<ProjectDto> test =
                    new PagedResponse<>(Collections.emptyList(), projectPage.getNumber(),
                            projectPage.getSize(), projectPage.getTotalElements(),
                            projectPage.getTotalPages(), projectPage.isLast());
            return new ResponseEntity<>(test, HttpStatus.NOT_FOUND);
        }
        List<ProjectDto> projectDTOS = projectPage.getContent().stream().map(this::entityToDto).toList();
        PagedResponse<ProjectDto> test = new PagedResponse<>(projectDTOS, projectPage.getNumber(), projectPage.getSize(), projectPage.getTotalElements(), projectPage.getTotalPages(),
                projectPage.isLast());
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedResponse<ProjectDto>> getAllProjectsByClass(Integer page, Integer size, Integer ClassId) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        Page<Project> projectByClassPage = projectRepository.findByProjectClassId(ClassId, pageable);
        if (projectByClassPage.getNumberOfElements() == 0) {
            PagedResponse<ProjectDto> test =
                    new PagedResponse<>(Collections.emptyList(), projectByClassPage.getNumber(),
                            projectByClassPage.getSize(), projectByClassPage.getTotalElements(),
                            projectByClassPage.getTotalPages(), projectByClassPage.isLast());
            return new ResponseEntity<>(test, HttpStatus.NOT_FOUND);
        }
        List<ProjectDto> projectDTOS = projectByClassPage.stream().map(this::entityToDto).toList();
        PagedResponse<ProjectDto> test = new PagedResponse<>(projectDTOS, projectByClassPage.getNumber(), projectByClassPage.getSize(), projectByClassPage.getTotalElements(), projectByClassPage.getTotalPages(),
                projectByClassPage.isLast());
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProjectDto>> getAllProjectsByClass(Integer classId) {
        List<Project> projects  = projectRepository.findByProjectClassId(classId);
        List<ProjectDto> projectDTOS = projects.stream().map(this::entityToDto).toList();

        return new ResponseEntity<>(projectDTOS, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<ProjectDto> getProjectById(Integer id) {
        Project project = projectRepository.findProjectById(id);
        if (project != null) {
            return new ResponseEntity<>(
                    entityToDto(project), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    //  =======-------=======-------sub-function-------=======-------=======

    private ProjectDto entityToDto(Project project) {
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
        ClassDto classDto = modelMapper.map(project.getProjectClass(), ClassDto.class);
        Set<UserDto> member = project.getMembers().stream().map(mem -> modelMapper.map(mem, UserDto.class)).collect(Collectors.toSet());
        projectDto.setMembers(member);
        projectDto.setClassDto(classDto);
        return projectDto;

    }

    private Project dtoToEntity(ProjectDto projectDto) {
        modelMapper.map(projectDto.getCreator(), User.class);
        Project project = modelMapper.map(projectDto,Project.class);
        Optional<ClassEntity> optClass = classRepository.findClassEntityById(projectDto.getClassDto().getId());
        User creator = userRepository.findUserById(projectDto.getCreator().getId());
        User mentor = userRepository.findUserById(projectDto.getMentor().getId());
        User leader = userRepository.findUserById(projectDto.getLeader().getId());
//        Set<User> members = projectDto.getMembers().stream().map( user ->  userRepository.findUserById(user.getId())).collect(Collectors.toSet());
//        System.out.println(optClass.get().getCode());
        project.setCreatedBy(creator.getFullName());
        project.setUpdatedBy(creator.getFullName());
        project.setCreator(creator);
        project.setMentor(mentor);
        project.setLeader(leader);
        project.setProjectClass(optClass.get());
        return project;

    }

    private Project dtoToEntity(ProjectDto projectDto, Project project) {
        User mentor = userRepository.findUserById(projectDto.getMentor().getId());
        UserDto leaderDto = projectDto.getLeader();
        if(leaderDto != null){
            User leader = userRepository.findUserById(leaderDto.getId());
            project.setLeader(leader);
        }
        if(projectDto.getMembers() != null){
            Set<User> members = new HashSet<User>();
            projectDto.getMembers().forEach(
                    mem -> {
                        User user = userRepository.findUserById(mem.getId());
                        members.add(user);
                    });

//            Set<User> members = projectDto.getMembers().stream().map( user ->  userRepository.findUserById(user.getId())).collect(Collectors.toSet());
//            Set<User> members = userRepository.findAllByProjectsIs()
            System.out.println(members);
            System.out.println("ok1");
            project.setMembers(members);
        }
        System.out.println("ok2");
        project.setUpdatedBy(mentor.getFullName());
        project.setMentor(mentor);
        project.setIsActive(projectDto.getIsActive());
        project.setProjectName(projectDto.getProjectName());
        project.setGroupName(projectDto.getGroupName());
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        return project;
    }
}
