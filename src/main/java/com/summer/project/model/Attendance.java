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
public class Attendance {
    @Id
    @Column(name="attendance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean attended;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @OneToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Attendance() {
    }

    public Attendance(boolean attended, LocalDate date, Student student, Schedule schedule, LocalDateTime created_at, LocalDateTime modified_at) {
        this.attended = attended;
        this.date = date;
        this.student = student;
        this.schedule = schedule;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Attendance(Long id, boolean attended, LocalDate date, Student student, Schedule schedule, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.attended = attended;
        this.date = date;
        this.student = student;
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

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public void setModified_at(LocalDateTime modifiedAt) {
        this.modified_at = modifiedAt;
    }
}
