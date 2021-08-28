package com.summer.project.repo;

import com.summer.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author user
    @project summer_project
    @class Students
    @version 1.0.0
    @since 8/20/2021 - 20.29
*/
public interface StudentRepository extends JpaRepository<Student, Long> {

}
