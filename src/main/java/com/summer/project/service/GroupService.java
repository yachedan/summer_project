package com.summer.project.service;

import com.summer.project.model.Group;
import com.summer.project.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @author user
    @project summer_project
    @class GroupService
    @version 1.0.0
    @since 8/24/2021 - 12.42
*/
@Service
public class GroupService {
    private final GroupRepository repository;

    public GroupService(GroupRepository repository){
        this.repository = repository;
    }

    public Group getById(Long id){
        return repository.findById(id).orElse(null);
    }
    public Group getByNumber(String number){
        return repository.findByNumber(number);
    }
    public  Group create(Group group){
        return repository.save(group);
    }

    public Group update(Group group){
        return repository.save(group);
    }

    public Group delete(Long id){
        var g = repository.findById(id).orElse(null);
        if(g != null){
            repository.delete(g);
        }
        return g;
    }
    public List<Group> getAll(){
        return repository.findAll();
    }
    public Page<Group> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }
}
