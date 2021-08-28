package com.summer.project.service;

import com.summer.project.model.Speciality;
import com.summer.project.repo.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class SpecialityService
    @version 1.0.0
    @since 8/24/2021 - 12.10
*/
@Service
public class SpecialityService {
    public final SpecialityRepository repository;
    
    public SpecialityService(SpecialityRepository repository) {
        this.repository = repository;
    }
    
    public Speciality getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Speciality create(Speciality Speciality){
        return repository.save(Speciality);
    }

    public Speciality update(Speciality Speciality){
        return repository.save(Speciality);
    }

    public  Speciality delete(Long id){
        var s = repository.findById(id).orElse(null);
        if(s != null)
            repository.delete(s);
        return s;
    }
    public  Speciality delete(Speciality s){
        if(s != null)
            repository.delete(s);
        return s;
    }
    public List<Speciality> getAll(){
        return repository.findAll();
    }
    public Page<Speciality> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
