package com.summer.project.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Schedule
    @version 1.0.0
    @since 8/26/2021 - 20.15
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
    @Id
    @Column(name="schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    @OneToOne
    @JoinColumn(name = "teaching_id", nullable = false)
    private Teaching teaching;
    private int day;
    private int time;
    private String type;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Schedule() {
    }

    public Schedule(Group group, Teaching teaching, int day, int time, String type, LocalDateTime created_at, LocalDateTime modified_at) {
        this.group = group;
        this.teaching = teaching;
        this.day = day;
        this.time = time;
        this.type = type;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Schedule(Long id, Group group, Teaching teaching, int day, int time, String type, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.group = group;
        this.teaching = teaching;
        this.day = day;
        this.time = time;
        this.type = type;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teaching getTeaching() {
        return teaching;
    }

    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
