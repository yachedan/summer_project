package com.summer.project.service;

import com.summer.project.model.Teacher;
import com.summer.project.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class TeacherService
    @version 1.0.0
    @since 8/24/2021 - 20.58
*/
@Service
public class TeacherService {
    public final TeacherRepository repository;

    public TeacherService(TeacherRepository repository){
        this.repository = repository;
    }

    public Teacher getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Teacher create(Teacher teacher){
        return repository.save(teacher);
    }

    public Teacher update(Teacher teacher){
        return repository.save(teacher);
    }

    public  Teacher delete(Long id){
        var s = repository.findById(id).orElse(null);
        if(s != null){
            repository.delete(s);
        }
        return s;
    }
    public  Teacher delete(Teacher s){
        if(s != null){
            repository.delete(s);
        }
        return s;
    }
    public List<Teacher> getAll(){
        return repository.findAll();
    }
    public Page<Teacher> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
