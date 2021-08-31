package com.summer.project.model;

import java.util.List;

/*
    @author user
    @project summer_project
    @class ListWrapper
    @version 1.0.0
    @since 8/30/2021 - 10.45
*/

public class ListWrapper {
    Attendance[][] tempAttendanceArray;

    public Attendance[][] getTempAttendanceArray() {
        return tempAttendanceArray;
    }

    public void setTempAttendanceArray(Attendance[][] tempAttendanceArray) {
        this.tempAttendanceArray = tempAttendanceArray;
    }
}
