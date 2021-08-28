package com.summer.project.service;

import com.summer.project.model.Teaching;
import com.summer.project.repo.TeachingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class TeachingService
    @version 1.0.0
    @since 8/25/2021 - 16.15
*/
@Service
public class TeachingService {
    private final TeachingRepository repository;

    public TeachingService(TeachingRepository repository){
        this.repository = repository;
    }

    public Teaching getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Teaching create(Teaching teaching){
        return repository.save(teaching);
    }

    public Teaching update(Teaching teaching){
        return repository.save(teaching);
    }

    public  Teaching delete(Long id){
        var t = repository.findById(id).orElse(null);
        if(t != null)
            repository.delete(t);
        return t;
    }
    public  Teaching delete(Teaching t){
        if(t != null)
            repository.delete(t);
        return t;
    }

    public List<Teaching> getAll(){
        return repository.findAll();
    }
    public Page<Teaching> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
