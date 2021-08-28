package com.summer.project.api.data;

import com.summer.project.model.Department;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class SubjectInterface
    @version 1.0.0
    @since 8/24/2021 - 23.18
*/
public interface SubjectInterface {
    /**
     * @return String
     */
    public Long getId();
    /**
     * @return String
     */
    public String getName();
    /**
     * @return LocalDateTime
     */
    public LocalDateTime getCreatedAt();
    /**
     * @return LocalDateTime
     */
    public LocalDateTime getModifiedAt();


    /**
     * @param id Long
     */
    public void setId(Long id);
    /**
     * @param name String
     */
    public void setName(String name);
    /**
     * @param createdAt LocalDateTime
     */
    public void setCreatedAt(LocalDateTime createdAt);
    /**
     * @param modifiedAt LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modifiedAt);
}
