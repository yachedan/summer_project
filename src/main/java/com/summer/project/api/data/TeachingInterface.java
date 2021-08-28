package com.summer.project.api.data;

import com.summer.project.model.Department;
import com.summer.project.model.Subject;
import com.summer.project.model.Teacher;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class TeachingInterface
    @version 1.0.0
    @since 8/24/2021 - 23.04
*/
public interface TeachingInterface {
    /**
     * @return String
     */
    public Long getId();
    /**
     * @return Subject
     */
    public Subject getSubject();
    /**
     * @return Teacher
     */
    public Teacher getTeacher();
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
     * @param subject Subject
     */
    public void setSubject(Subject subject);
    /**
     * @param teacher Teacher
     */
    public void setTeacher(Teacher teacher);
    /**
     * @param createdAt LocalDateTime
     */
    public void setCreatedAt(LocalDateTime createdAt);
    /**
     * @param modifiedAt LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modifiedAt);
}
