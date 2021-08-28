package com.summer.project.service;

import com.summer.project.model.Faculty;
import com.summer.project.repo.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class FacultyServic
    @version 1.0.0
    @since 8/23/2021 - 01.08
*/
@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService (FacultyRepository repository){
        this.repository = repository;
    }

    public Faculty getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public  Faculty create(Faculty faculty){
        return repository.save(faculty);
    }

    public Faculty update(Faculty faculty){
        return repository.save(faculty);
    }

    public Faculty delete(Long id) {
        var g = repository.findById(id).orElse(null);
        if (g != null)
            repository.delete(g);
        return g;
    }

    public List<Faculty> getAll(){
        return repository.findAll();
    }
    public Page<Faculty> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
