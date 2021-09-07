package com.summer.project.repo;

import com.summer.project.model.Journal;
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
public interface JournalRepository extends JpaRepository<Journal, Long> {
    Page<Journal> findAllByConductScheduleId(Long id, Pageable pageable);

    Journal findByStudentIdAndConductDate(Long id, LocalDate date);
    void deleteAllByStudentGroupNumberAndConductDate(String number, LocalDate date);
}
