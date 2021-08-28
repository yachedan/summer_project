package com.summer.project.service;

import com.summer.project.model.Subject;
import com.summer.project.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class SubjectService
    @version 1.0.0
    @since 8/24/2021 - 23.25
*/
@Service
public class SubjectService {
    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository){
        this.repository = repository;
    }

    public Subject getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Subject create(Subject subject){
        return repository.save(subject);
    }

    public Subject update(Subject subject){
        return repository.save(subject);
    }

    public  Subject delete(Long id){
        var s = repository.findById(id).orElse(null);
        if(s != null){
            repository.delete(s);
        }
        return s;
    }

    public List<Subject> getAll(){
        return repository.findAll();
    }
    public Page<Subject> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
