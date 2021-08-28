package com.summer.project.repo;

import com.summer.project.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    @author user
    @project summer_project
    @class GroupRepository
    @version 1.0.0
    @since 8/24/2021 - 12.43
*/
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByNumber(String number);
}
