package com.summer.project.service;

import com.summer.project.model.Attendance;
import com.summer.project.repo.AttendanceRepository;
import com.summer.project.repo.AttendanceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/*
    @author user
    @project summer_project
    @class AttendanceService
    @version 1.0.0
    @since 8/28/2021 - 11.43
*/
@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository){
        this.repository = repository;
    }

    public Attendance getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public  Attendance create(Attendance Attendance){
        return repository.save(Attendance);
    }

    public Attendance update(Attendance Attendance){
        return repository.save(Attendance);
    }

    public Attendance delete(Long id){
        var g = repository.findById(id).orElse(null);
        if(g != null){
            repository.delete(g);
        }
        return g;
    }
    public List<Attendance> getAll(){
        return repository.findAll();
    }
    public Page<Attendance> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public Page<Attendance> getAllBySchedule(Long id, Pageable pageable) {return  repository.findAllByScheduleId(id, pageable);}
    public Attendance getByStudentAndDate(Long id, LocalDate date) {return  repository.findByStudentIdAndDate(id, date);}
    public int deleteAllByGroupAndDate(String number, LocalDate date){
        repository.deleteAllByStudentGroupNumberAndDate(number, date);
        return -1;
    }
}
