package com.summer.project.service;

import com.summer.project.model.Department;
import com.summer.project.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class DepartmentService
    @version 1.0.0
    @since 8/23/2021 - 22.59
*/
@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository){
        this.repository = repository;
    }

    public Department getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Department create(Department Department){
        return repository.save(Department);
    }

    public Department update(Department Department){
        return repository.save(Department);
    }

    public  Department delete(Long id){
        var s = repository.findById(id).orElse(null);
        if(s != null)
            repository.delete(s);
        return s;
    }

    public List<Department> getAll(){
        return repository.findAll();
    }
    public Page<Department> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
