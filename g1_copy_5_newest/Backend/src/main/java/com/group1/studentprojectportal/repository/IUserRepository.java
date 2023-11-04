package com.group1.studentprojectportal.repository;


import com.group1.studentprojectportal.constant.Roles;
import com.group1.studentprojectportal.entity.ClassEntity;
import com.group1.studentprojectportal.entity.Project;
import com.group1.studentprojectportal.entity.User;
import com.group1.studentprojectportal.payload.ClassDto;
import com.group1.studentprojectportal.payload.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Set;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    User findUserByEmail(String email);

    boolean existsByEmail(String email);

    Set<User> findByClasses(ClassEntity classEntity);

    Page<User> findUsersByClasses_Id(Integer classes_id, Pageable pageable);

    List<User> findUsersByRole(Roles role);

    Page<User> findUsersByRole(Roles role, Pageable pageable);

    Page<User> findUsersByFullNameContainingAndRole(String fullName, Roles roles, Pageable pageable);

    Page<User> findUsersByIsEnableAndIsVerifiedAndRoleAndFullNameContaining(
            boolean isEnabled, boolean isVerified,
            Roles roles, String fullName, Pageable pageable
    );
    Page<User> findUsersByIsEnableAndIsVerifiedAndFullNameContaining(
            boolean isEnabled, boolean isVerified,
             String fullName, Pageable pageable
    );
    Page<User> findByFullNameContaining(
             String fullName, Pageable pageable
    );

    Page<User> findUsersByIsEnableAndIsVerified(
            boolean isEnabled, boolean isVerified, Pageable pageable
    );

    List<User> findAllByProjectsIsEmptyAndClassesIs(ClassEntity classEntity);

    List<User> findAllByClassesIsAndProjectsIsEmpty(ClassEntity classEntity);

    Set<User> findAllByProjectsIs(Project project);

//    @Query("SELECT u FROM User u LEFT JOIN u.classes c LEFT JOIN u.projects p WHERE c.id = :classId AND p IS NULL")
//    List<User> findUserByClass_IdAndProjectsIsEmpty(Integer classId);

//    List<User> findAllByProjectsIsEmpty(); //dung duoc


}
