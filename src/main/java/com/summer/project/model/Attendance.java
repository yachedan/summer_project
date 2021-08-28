package com.summer.project.model;

import java.time.LocalDate;

/*
    @author user
    @project summer_project
    @class Attendance
    @version 1.0.0
    @since 8/26/2021 - 20.14
*/
public class Attendance {
    private Long id;
    private boolean attended;
    private LocalDate date;
    private Student student;
}
