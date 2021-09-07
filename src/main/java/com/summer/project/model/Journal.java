package com.summer.project.model;

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
    @class Attendance
    @version 1.0.0
    @since 8/26/2021 - 20.14
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Journal {
    @Id
    @Column(name="journal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @OneToOne
    @JoinColumn(name = "conduct_id", nullable = false)
    private Conduct conduct;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Journal() {
    }

    public Journal(String value, Student student, Conduct conduct, LocalDateTime created_at, LocalDateTime modified_at) {
        this.value = value;
        this.student = student;
        this.conduct = conduct;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Journal(Long id, String value, Student student, Conduct conduct, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.value = value;
        this.student = student;
        this.conduct = conduct;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Conduct getConduct() {
        return conduct;
    }

    public void setConduct(Conduct conduct) {
        this.conduct = conduct;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.created_at = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modified_at;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modified_at = modifiedAt;
    }
}
