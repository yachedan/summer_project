package com.summer.project.repo;

import com.summer.project.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author user
    @project summer_project
    @class FacultyInterface
    @version 1.0.0
    @since 8/21/2021 - 17.08
*/
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
