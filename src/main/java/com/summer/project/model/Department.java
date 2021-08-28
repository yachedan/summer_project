package com.summer.project.model;

import com.summer.project.api.data.DepartmentInterface;
import com.summer.project.api.data.FacultyInterface;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Department
    @version 1.0.0
    @since 8/21/2021 - 14.01
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Department implements DepartmentInterface {
    @Id
    @Column(name="department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortName;
    private String head;
    @OneToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Department() {
    }

    public Department(String name, String shortName, String head, Faculty faculty, LocalDateTime created_at, LocalDateTime modified_at) {
        this.name = name;
        this.shortName = shortName;
        this.head = head;
        this.faculty = faculty;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Department(Long id, String name, String shortName, String head, Faculty faculty, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.head = head;
        this.faculty = faculty;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    /**
     * @return String
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return String
     */
    public String getShortName() {
        return this.shortName;
    }

    /**
     * @return String
     */
    public String getHead() {
        return this.head;
    }

    /**
     * @return FacultyInterface
     */
    public Faculty getFaculty() {
        return this.faculty;
    }

    /**
     * @return LocalDateTime
     */
    public LocalDateTime getCreatedAt() {
        return this.created_at;
    }

    /**
     * @return LocalDateTime
     */
    public LocalDateTime getModifiedAt() {
        return this.modified_at;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    public void setHead(String head) {
        this.head = head;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.created_at = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modified_at = modifiedAt;
    }
}
