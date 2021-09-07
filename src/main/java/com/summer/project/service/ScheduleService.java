package com.summer.project.service;

import com.summer.project.model.Group;
import com.summer.project.model.Schedule;
import com.summer.project.repo.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class ScheduleService
    @version 1.0.0
    @since 8/26/2021 - 20.38
*/
@Service
public class ScheduleService {
    private final ScheduleRepository repository;
    
    public ScheduleService(ScheduleRepository repository){
        this.repository = repository;
    }

    public Schedule getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public  Schedule create(Schedule Schedule){
        return repository.save(Schedule);
    }

    public Schedule update(Schedule Schedule){
        return repository.save(Schedule);
    }

    public Schedule delete(Long id){
        var g = repository.findById(id).orElse(null);
        if(g != null){
            repository.delete(g);
        }
        return g;
    }
    public List<Schedule> getAll(){
        return repository.findAll();
    }
    public Page<Schedule> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public List<Schedule> getByGroup(Long id){ return repository.findByGroupIdOrderByTime(id);}
}
