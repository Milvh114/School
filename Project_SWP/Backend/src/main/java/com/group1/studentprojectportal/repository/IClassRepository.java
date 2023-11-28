package com.group1.studentprojectportal.repository;

import com.group1.studentprojectportal.entity.ClassEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClassRepository extends JpaRepository<ClassEntity, Integer> {
    boolean existsByIdOrCode(Integer id, String code);
    Optional<ClassEntity> findClassEntityById(Integer id);

    Page<ClassEntity> findClassEntitiesBySubject_Id(Integer subject_id, Pageable pageable);

}
