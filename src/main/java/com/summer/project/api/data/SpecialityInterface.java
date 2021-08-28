package com.summer.project.api.data;

import com.summer.project.model.Department;

import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class SpecialityInterface
    @version 1.0.0
    @since 8/21/2021 - 14.42
*/
public interface SpecialityInterface {
    /**
     * @return String
     */
    public Long getId();
    /**
     * @return String
     */
    public String getName();
    /**
     * @return String
     */
    public String getNumber();
    /**
     * @return Department
     */
    public Department getDepartment();
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
     * @param number String
     */
    public void setNumber(String number);
    /**
     * @param department Department
     */
    public void setDepartment(Department department);
    /**
     * @param createdAt LocalDateTime
     */
    public void setCreatedAt(LocalDateTime createdAt);
    /**
     * @param modifiedAt LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modifiedAt);
}
