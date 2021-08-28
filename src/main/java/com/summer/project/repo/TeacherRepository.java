package com.summer.project.repo;

import com.summer.project.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author user
    @project summer_project
    @class TeacherRepository
    @version 1.0.0
    @since 8/24/2021 - 20.57
*/
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
