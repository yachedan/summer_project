package com.summer.project.model;

import com.summer.project.api.data.SpecialityInterface;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Speciality
    @version 1.0.0
    @since 8/21/2021 - 14.53
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Speciality implements SpecialityInterface {
    @Id
    @Column(name="speciality_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    @OneToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Speciality() {
    }

    public Speciality(String name, String number, LocalDateTime created_at, LocalDateTime modified_at) {
        this.name = name;
        this.number = number;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Speciality(Long id, String name, String number, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
        this.number = number;
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
    public String getNumber() {
        return this.number;
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
     * @param number String
     */
    public void setNumber(String number) {
        this.number = number;
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
