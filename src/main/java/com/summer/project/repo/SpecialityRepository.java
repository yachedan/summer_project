package com.summer.project.repo;

import com.summer.project.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    @author user
    @project summer_project
    @class SpecialityRepository
    @version 1.0.0
    @since 8/24/2021 - 12.09
*/
@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}
