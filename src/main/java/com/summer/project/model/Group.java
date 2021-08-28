package com.summer.project.model;

import com.summer.project.api.data.GroupInterface;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Group
    @version 1.0.0
    @since 8/20/2021 - 21.02
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="\"Group\"")
public class Group implements GroupInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="group_id")
    private Long id;
    private String number;
    private int amount;
    @OneToOne
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime modified_at;

    public Group() {
    }

    public Group(String number, int amount, Speciality speciality, LocalDateTime created_at, LocalDateTime modified_at) {
        this.number = number;
        this.amount = amount;
        this.speciality = speciality;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Group(Long id, String number, int amount, Speciality speciality, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.speciality = speciality;
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
     * @return int
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @return Speciality
     */
    public Speciality getSpeciality() {
        return this.speciality;
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
     * @param amount int
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @param speciality Speciality
     */
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
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
