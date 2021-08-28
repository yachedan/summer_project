package com.summer.project.model;

import com.summer.project.api.data.StudentInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @class Student
    @version 1.0.0
    @since 8/20/2021 - 20.48
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Student implements StudentInterface {
    @Id
    @Column(name="student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @OneToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Student() {
    }

    public Student(String number, String name, LocalDate dateOfBirth, Group group, LocalDateTime created_at, LocalDateTime modified_at) {
        this.number = number;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.group = group;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Student(Long id, String number, String name, LocalDate dateOfBirth, Group group, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.group = group;
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
    public String getNumber() {
        return this.number;
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
     * @return Group
     */
    public Group getGroup() {
        return this.group;
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
     * @param number String
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param dateOfBirth LocalDate
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @param group Group
     */
    public void setGroup(Group group) {
        this.group = group;
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
