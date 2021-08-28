package com.summer.project.repo;

import com.summer.project.model.Group;
import com.summer.project.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    @author user
    @project summer_project
    @class ScheduleRepository
    @version 1.0.0
    @since 8/26/2021 - 20.37
*/
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public List<Schedule> findByGroupNumberOrderByTime(String number);

}
