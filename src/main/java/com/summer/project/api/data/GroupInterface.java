package com.summer.project.api.data;

import com.summer.project.model.Speciality;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class Group
    @version 1.0.0
    @since 8/20/2021 - 21.50
*/
public interface GroupInterface {

    /**
     * @return String
     */
    public Long getId();
    /**
     * @return String
     */
    public String getNumber();
    /**
     * @return int
     */
    public int getAmount();
    /**
     * @return Speciality
     */
    public Speciality getSpeciality();
    /**
     * @return LocalDateTime
     */
    public LocalDateTime getCreatedAt();
    /**
     * @return LocalDateTime
     */
    public LocalDateTime getModifiedAt();

    /**
     * @param id Long
     */
    public void setId(Long id);
    /**
     * @param number String
     */
    public void setNumber(String number);
    /**
     * @param amount int
     */
    public void setAmount(int amount);
    /**
     * @param speciality Speciality
     */
    public void setSpeciality(Speciality speciality);
    /**
     * @param createdAt LocalDateTime
     */
    public void setCreatedAt(LocalDateTime createdAt);
    /**
     * @param modifiedAt LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modifiedAt);

}
