package com.summer.project.api.data;

import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class FacultyInterface
    @version 1.0.0
    @since 8/21/2021 - 12.30
*/
public interface FacultyInterface {

    /**
     * @return String
     */
    public Long getId();
    /**
     * @return String
     */
    public String getName();
    /**
     * @return String
     */
    public String getShortName();
    /**
     * @return String
     */
    public String getPhone();
    /**
     * @return String
     */
    public String getAddress();
    /**
     * @return String
     */
    public String getDean();
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
     * @param name String
     */
    public void setName(String name);
    /**
     * @param shortName String
     */
    public void setShortName(String shortName);
    /**
     * @param phone String
     */
    public void setPhone(String phone);
    /**
     * @param address String
     */
    public void setAddress(String address);
    /**
     * @param dean String
     */
    public void setDean(String dean);
    /**
     * @param createdAt LocalDateTime
     */
    public void setCreatedAt(LocalDateTime createdAt);
    /**
     * @param modifiedAt LocalDateTime
     */
    public void setModifiedAt(LocalDateTime modifiedAt);

}
