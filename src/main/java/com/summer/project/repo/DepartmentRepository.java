package com.summer.project.repo;

import com.summer.project.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    @author user
    @project summer_project
    @class DepartmentInterface
    @version 1.0.0
    @since 8/23/2021 - 22.58
*/
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
