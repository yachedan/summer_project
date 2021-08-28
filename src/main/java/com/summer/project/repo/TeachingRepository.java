package com.summer.project.repo;

import com.summer.project.model.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author user
    @project summer_project
    @class TeachingRepository
    @version 1.0.0
    @since 8/25/2021 - 16.14
*/
public interface TeachingRepository extends JpaRepository<Teaching, Long> {
}
