package com.summer.project.controller.ui;

import com.summer.project.model.*;
import com.summer.project.service.AttendanceService;
import com.summer.project.service.ScheduleService;
import com.summer.project.service.StudentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
    @author user
    @project summer_project
    @class AttendanceUIController
    @version 1.0.0
    @since 8/28/2021 - 11.42
*/
@Service
@RequestMapping("ui/attendance")
public class AttendanceUIController {
    private final AttendanceService attendanceService;
    private final StudentService studentService;
    private final ScheduleService scheduleService;

    public AttendanceUIController(AttendanceService attendanceService, StudentService studentService,
                                ScheduleService scheduleService){
        this.attendanceService = attendanceService;
        this.studentService = studentService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/get/all")
    public String getEntries(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("entries",attendanceService.getAll(PageRequest.of(page,size)));
        return "get-attendance";
    }
    @GetMapping("/get/{number}")
    public String getEntriesByGroup(@PathVariable("number")String number, HttpServletRequest request, Model model){
        List<Schedule> entries = scheduleService.getByGroup(number);
        Group group = entries.get(0).getGroup();
        Schedule[][] array = new Schedule[9][5];
        for(int i=0; i<9; i++)
            for(int j=0; j<5; j++)
                array[i][j] = new Schedule(null, group, new Teaching(null, new Subject(null, "-",null,null)
                        ,new Teacher(null, "-",null,null,null,null, null),
                        null,null),j,i,null,null,null);
        for(Schedule entry : entries){
            array[entry.getTime()][entry.getDay()] = entry;
        }
        for(int i = 0; i < 9; i++){
            model.addAttribute("row"+i, array[i]);
        }
        return "get-attendance-group";
    }
    @GetMapping("/table/{number}/{time}/{day}/{id}")
    public String showTable(@PathVariable("id") Long id,@PathVariable("number") String number,@PathVariable("time")
            int time, @PathVariable("day") int day, Attendance attendance,HttpServletRequest request, ListWrapper list, Model model) {

        List<Student> students = studentService.getAllByGroup(number);
        int page = 0;
        int size = 5*students.size();
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("entries", attendanceService.getAllBySchedule(id,PageRequest.of(page,size)));
        List<Attendance> entries = attendanceService.getAllBySchedule(id,PageRequest.of(page,size)).getContent();

        Attendance[][] array = new Attendance[students.size()][];
        boolean f = true;
        for(int i = 0; i < students.size(); i++){
            int finalI = i;
            List<Attendance> entriesFiltered = entries.stream()
                    .filter(entry -> entry.getStudent() == students.get(finalI))
                    .sorted(Comparator.comparing(Attendance::getDate)).collect(Collectors.toList());
            array[i] = new Attendance[entriesFiltered.size()];
            for(int j = 0; j < entriesFiltered.size(); j++){
                array[i][j] = entriesFiltered.get(j);
            }
            if (f) {
                List<LocalDate> dates = entriesFiltered.stream()
                        .map(Attendance::getDate).distinct().collect(Collectors.toList());
                model.addAttribute("dates",dates);
                f = false;
            }
        }
        /*for(Student student : students){
            list.setTempAttendanceList(entries.stream()
                    .filter(entry->entry.getStudent() == student).collect(Collectors.toList()));
            for(int i = 0; i < list.getTempAttendanceList().size(); i++){
                model.addAttribute("entry"+list.getTempAttendanceList().get(i).getStudent().getId()+i, list
                        .getTempAttendanceList().get(i));
            }
            if (f) {
                List<LocalDate> dates = list.getTempAttendanceList().stream()
                        .map(Attendance::getDate).distinct().collect(Collectors.toList());
                model.addAttribute("dates",dates);
                f = false;
            }
            model.addAttribute("row"+student.getId(),list);
        }

        for(Attendance entry : entries){
            model.addAttribute("attendance"+entry.getStudent(),entry);
        }*/
        list.setTempAttendanceArray(array);
        model.addAttribute("list",list);
        model.addAttribute("students", students);
        model.addAttribute(id);
        model.addAttribute(number);
        model.addAttribute(day);
        model.addAttribute(time);
        model.addAttribute("attendance", attendance);
        return "table-attendance";
    }
    @RequestMapping("/table/{number}/{time}/{day}/{id}/add")
    public String addColumn(@PathVariable("id") Long id, @PathVariable("number") String number, @PathVariable("time")
            int time, @PathVariable("day") int day, Attendance attendance,BindingResult bindingResult, Model model) {

        List<Student> students = studentService.getAllByGroup(number);
        for(Student student : students){
            if(attendanceService.getByStudentAndDate(student.getId(), attendance.getDate()) != null)
                continue;
            Attendance entry = new Attendance(false,attendance.getDate(),student,scheduleService.getById(id),
                    null,null);
            attendanceService.create(entry);
        }

        return "redirect:/ui/attendance/table/"+number+"/"+time+"/"+day+"/"+id;
    }
    @RequestMapping("/table/{number}/{time}/{day}/{id}/change")
    public String changeAttended(@PathVariable("id") Long id, @PathVariable("number") String number, @PathVariable("time")
            int time, @PathVariable("day") int day, ListWrapper list,BindingResult bindingResult, Model model) {

        var array = list.getTempAttendanceArray();
        for(Attendance[] row : array)
            for(Attendance attendance : row){
                boolean attended = attendance.isAttended();
                attendance = attendanceService.getById(attendance.getId());
                attendance.setAttended(attended);
                attendanceService.update(attendance);
            }

        return "redirect:/ui/attendance/table/"+number+"/"+time+"/"+day+"/"+id;
    }
    @RequestMapping("/table/{number}/{time}/{day}/{id}/delete/{date}")
    public String deleteDate(@PathVariable("id") Long id, @PathVariable("number") String number, @PathVariable("time")
            int time, @PathVariable("day") int day,
                             @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                             Model model) {

        attendanceService.deleteAllByGroupAndDate(number, date);
        return "redirect:/ui/attendance/table/"+number+"/"+time+"/"+day+"/"+id;
    }
    @GetMapping("/signup")
    public String showSignupForm(Attendance attendance, Model model){
        List<Student> students = studentService.getAll();
        List<Schedule> schedules = scheduleService.getAll();
        model.addAttribute("attendance",attendance);
        model.addAttribute("students", students);
        model.addAttribute("schedules", schedules);
        return "create-attendance";
    }
    @PostMapping("/create")
    public String create(Attendance attendance, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-attendance";
        attendanceService.create(attendance);
        return "redirect:/ui/attendance/get/all";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Attendance attendance = attendanceService.getById(id);
        if(attendance == null)
            return ("Invalid attendanced Id:"+id);
        List<Student> students = studentService.getAll();
        List<Schedule> schedules = scheduleService.getAll();
        model.addAttribute("attendance",attendance);
        model.addAttribute("students", students);
        model.addAttribute("schedules", schedules);

        return "update-attendance";
    }
    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable("id") Long id, Attendance attendance,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            attendance.setId(id);
            return "update-attendance";
        }

        attendanceService.update(attendance);
        return "redirect:/ui/attendance/get/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable("id") Long id, Model model) {
        Attendance attendance = attendanceService.getById(id);
        if (attendance == null)
            return ("Invalid attendance Id:" + id);
        attendanceService.delete(id);
        return "redirect:/ui/attendance/get/all";
    }
}
