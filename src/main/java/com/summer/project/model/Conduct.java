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
    @class Conduct
    @version 1.0.0
    @since 8/31/2021 - 18.56
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Conduct {
    @Id
    @Column(name="conduct_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean conducted;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Conduct() {
    }

    public Conduct(boolean conducted, LocalDate date, Schedule schedule, LocalDateTime created_at, LocalDateTime modified_at) {
        this.conducted = conducted;
        this.date = date;
        this.schedule = schedule;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Conduct(Long id, boolean conducted, LocalDate date, Schedule schedule, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.conducted = conducted;
        this.date = date;
        this.schedule = schedule;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isConducted() {
        return conducted;
    }

    public void setConducted(boolean conducted) {
        this.conducted = conducted;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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
