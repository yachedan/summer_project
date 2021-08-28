package com.summer.project.api.data;

import com.summer.project.model.Department;
import com.summer.project.model.Group;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class TeacherInterface
    @version 1.0.0
    @since 8/24/2021 - 20.45
*/
public interface TeacherInterface {
    /**
     * @return String
     */
    public Long getId();
    /**
     * @return String
     */
    public String getName();
    /**
     * @return LocalDate
     */
    public LocalDate getDateOfBirth();
    /**
     * @return String
     */
    public String getPosition();
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
     * @param position String
     */
    public void setPosition(String position);
    /**
     * @param dateOfBirth LocalDate
     */
    public void setDateOfBirth(LocalDate dateOfBirth);
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
