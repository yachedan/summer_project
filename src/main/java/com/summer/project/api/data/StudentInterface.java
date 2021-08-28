package com.summer.project.api.data;

/*
    @author user
    @project summer_project
    @class StudentInterface
    @version 1.0.0
    @since 8/20/2021 - 21.11
*/

import com.summer.project.model.Group;
import com.summer.project.model.Speciality;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface StudentInterface {

    /**
     * @return String
     */
    public Long getId();
    /**
     * @return String
     */
    public String getNumber();
    /**
     * @return String
     */
    public String getName();
    /**
     * @return LocalDate
     */
    public LocalDate getDateOfBirth();
    /**
     * @return Group
     */
    public Group getGroup();
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
     * @param number String
     */
    public void setNumber(String number);
    /**
     * @param name String
     */
    public void setName(String name);
    /**
     * @param dateOfBirth LocalDate
     */
    public void setDateOfBirth(LocalDate dateOfBirth);
    /**
     * @param group Group
     */
    public void setGroup(Group group);

    /**
     * @param createdAt LocalDateTime
     */
    public void setCreatedAt(LocalDateTime createdAt);
    /**
     * @param modifiedAt LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modifiedAt);

}
