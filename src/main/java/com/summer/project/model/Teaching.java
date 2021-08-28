package com.summer.project.model;

import com.summer.project.api.data.TeachingInterface;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Teaching
    @version 1.0.0
    @since 8/25/2021 - 16.07
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Teaching implements TeachingInterface {
    @Id
    @Column(name="teaching_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @OneToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Teaching() {
    }

    public Teaching(Subject subject, Teacher teacher, LocalDateTime created_at, LocalDateTime modified_at) {
        this.subject = subject;
        this.teacher = teacher;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Teaching(Long id, Subject subject, Teacher teacher, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
