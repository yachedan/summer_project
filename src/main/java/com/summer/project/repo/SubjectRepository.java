package com.summer.project.repo;

import com.summer.project.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author user
    @project summer_project
    @class SubjectRepository
    @version 1.0.0
    @since 8/24/2021 - 23.23
*/
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
