package com.summer.project.repo;

import com.summer.project.model.Conduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    @author user
    @project summer_project
    @class ConductRepository
    @version 1.0.0
    @since 8/31/2021 - 19.15
*/
@Repository
public interface ConductRepository extends JpaRepository<Conduct, Long> {
    Page<Conduct> findAllByScheduleId(Long id, Pageable pageable);
    List<Conduct> findAllByScheduleId(Long id);

}
