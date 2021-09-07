package com.summer.project.service;

import com.summer.project.model.Conduct;
import com.summer.project.repo.ConductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/*
    @author user
    @project summer_project
    @class ConductService
    @version 1.0.0
    @since 8/31/2021 - 19.15
*/
@Service
public class ConductService {
    private final ConductRepository repository;

    public ConductService(ConductRepository repository){
        this.repository = repository;
    }

    public Conduct getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public  Conduct create(Conduct Conduct){
        return repository.save(Conduct);
    }

    public Conduct update(Conduct Conduct){
        return repository.save(Conduct);
    }

    public Conduct delete(Long id){
        var g = repository.findById(id).orElse(null);
        if(g != null){
            repository.delete(g);
        }
        return g;
    }
    public List<Conduct> getAll(){
        return repository.findAll();
    }
    public Page<Conduct> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public Page<Conduct> getAllBySchedule(Long id, Pageable pageable){
        return repository.findAllByScheduleId(id, pageable);
    }
    public List<Conduct> getAllBySchedule(Long id) {
        return repository.findAllByScheduleId(id);
    }
    }
