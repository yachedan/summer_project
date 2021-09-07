package com.summer.project.service;

import com.summer.project.model.Journal;
import com.summer.project.repo.JournalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/*
    @author user
    @project summer_project
    @class JournalService
    @version 1.0.0
    @since 8/28/2021 - 11.43
*/
@Service
@Transactional
public class JournalService {
    private final JournalRepository repository;

    public JournalService(JournalRepository repository){
        this.repository = repository;
    }

    public Journal getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public  Journal create(Journal Journal){
        return repository.save(Journal);
    }

    public Journal update(Journal Journal){
        return repository.save(Journal);
    }

    public Journal delete(Long id){
        var g = repository.findById(id).orElse(null);
        if(g != null){
            repository.delete(g);
        }
        return g;
    }
    public List<Journal> getAll(){
        return repository.findAll();
    }
    public Page<Journal> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public Page<Journal> getAllBySchedule(Long id, Pageable pageable) {return  repository.findAllByConductScheduleId(id, pageable);}
    public Journal getByStudentAndDate(Long id, LocalDate date) {return  repository
            .findByStudentIdAndConductDate(id, date);}
    public void deleteAllByGroupAndDate(String number, LocalDate date){
        repository.deleteAllByStudentGroupNumberAndConductDate(number, date);
    }
}
