package com.summer.project.model;

import com.summer.project.api.data.TeacherInterface;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Teacher
    @version 1.0.0
    @since 8/24/2021 - 20.49
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Teacher implements TeacherInterface {
    @Id
    @Column(name="teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String position;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @OneToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Teacher() {
    }

    public Teacher(String name, String position, LocalDate dateOfBirth, Department department, LocalDateTime created_at, LocalDateTime modified_at) {
        this.name = name;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Teacher(Long id, String name, String position, LocalDate dateOfBirth, Department department, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
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
     * @return LocalDate
     */
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * @return String
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * @return Department
     */
    public Department getDepartment() {
        return this.department;
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

    /**
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param position String
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @param dateOfBirth LocalDate
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @param department Department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @param createdAt LocalDateTime
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.created_at = createdAt;
    }

    /**
     * @param modifiedAt LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modified_at = modifiedAt;
    }
}
