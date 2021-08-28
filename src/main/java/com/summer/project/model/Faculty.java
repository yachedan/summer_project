package com.summer.project.model;

import com.summer.project.api.data.FacultyInterface;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Faculty
    @version 1.0.0
    @since 8/21/2021 - 12.40
*/
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Faculty implements FacultyInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="faculty_id")
    private Long id;
    private String name;
    private String shortName;
    private String phone;
    private String address;
    private String dean;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modified_at;

    public Faculty() {
    }

    public Faculty(String name, String shortName, String phone, String address, String dean, LocalDateTime created_at, LocalDateTime modified_at) {
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.address = address;
        this.dean = dean;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Faculty(Long id, String name, String shortName, String phone, String address, String dean, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.phone = phone;
        this.address = address;
        this.dean = dean;
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
    public String getShortName() {
        return this.shortName;
    }

    /**
     * @return String
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * @return String
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @return String
     */
    public String getDean() {
        return this.dean;
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
     * @param shortName String
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @param phone String
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param dean String
     */
    public void setDean(String dean) {
        this.dean = dean;
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
