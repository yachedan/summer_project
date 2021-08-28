package com.summer.project.model;

import com.summer.project.api.data.SubjectInterface;
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
    @class Subject
    @version 1.0.0
    @since 8/24/2021 - 23.20
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subject implements SubjectInterface {
    @Id
    @Column(name="subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Subject() {
    }

    public Subject(String name, LocalDateTime created_at, LocalDateTime modified_at) {
        this.name = name;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Subject(Long id, String name, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
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
