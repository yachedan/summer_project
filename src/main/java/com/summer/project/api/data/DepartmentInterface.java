package com.summer.project.api.data;

import com.summer.project.model.Faculty;

import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class DepartmentInterface
    @version 1.0.0
    @since 8/21/2021 - 13.16
*/
public interface DepartmentInterface {
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
    public String getShortName();
    /**
     * @return String
     */
    public String getHead();
    /**
     * @return FacultyInterface
     */
    public FacultyInterface getFaculty();
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
     * @param shortName String
     */
    public void setShortName(String shortName);
    /**
     * @param head String
     */
    public void setHead(String head);
    /**
     * @param faculty Faculty
     */
    public void setFaculty(Faculty faculty);
    /**
     * @param created_at LocalDateTime
     */
    public void setCreatedAt(LocalDateTime created_at);
    /**
     * @param modified_at LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modified_at);
}
