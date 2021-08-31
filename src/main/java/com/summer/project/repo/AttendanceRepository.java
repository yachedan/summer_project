package com.summer.project.repo;

import com.summer.project.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

/*
    @author user
    @project summer_project
    @class AttendanceRepository
    @version 1.0.0
    @since 8/28/2021 - 11.43
*/
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Page<Attendance> findAllByScheduleId(Long id, Pageable pageable);

    Attendance findByStudentIdAndDate(Long id, LocalDate date);
    void deleteAllByStudentGroupNumberAndDate(String number, LocalDate date);
}
