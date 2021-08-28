package com.summer.project.controller.ui;

import com.summer.project.model.*;
import com.summer.project.service.GroupService;
import com.summer.project.service.ScheduleService;
import com.summer.project.service.TeachingService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/*
    @author user
    @project summer_project
    @class ScheduleUIController
    @version 1.0.0
    @since 8/26/2021 - 20.38
*/
@Service
@RequestMapping("ui/schedule")
public class ScheduleUIController {
    private final ScheduleService scheduleService;
    private final GroupService groupService;
    private final TeachingService teachingService;

    public ScheduleUIController(ScheduleService scheduleService, GroupService groupService, TeachingService teachingService){
        this.scheduleService = scheduleService;
        this.groupService = groupService;
        this.teachingService = teachingService;
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
        model.addAttribute("entries",scheduleService.getAll(PageRequest.of(page,size)));
        return "get-schedule";
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
        return "get-schedule-group";
    }
    @GetMapping("/signup")

    public String showSignupForm(Schedule schedule, Model model){
        List<Group> groups = groupService.getAll();
        List<Teaching> teachings = teachingService.getAll();
        model.addAttribute("schedule",schedule);
        model.addAttribute("groups", groups);
        model.addAttribute("teachings", teachings);
        return "create-schedule";
    }
    @PostMapping("/create")
    public String create(Schedule schedule, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-schedule";
        scheduleService.create(schedule);
        return "redirect:/ui/schedule/get/all";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Schedule schedule = scheduleService.getById(id);
        if(schedule == null)
            return ("Invalid schedule Id:"+id);
        List<Group> groups = groupService.getAll();
        List<Teaching> teachings = teachingService.getAll();
        model.addAttribute("schedule",schedule);
        model.addAttribute("groups", groups);
        model.addAttribute("teachings", teachings);

        return "update-schedule";
    }
    @GetMapping("/update/{number}/{time}/{day}/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,@PathVariable("number") String number,@PathVariable("time")
            int time, @PathVariable("day") int day, Model model) {
            Schedule schedule = scheduleService.getById(id);
        List<Teaching> teachings = teachingService.getAll();
        model.addAttribute("schedule",schedule);
        model.addAttribute("teachings", teachings);
        model.addAttribute("schedule",schedule);
        return "update-schedule-group";
    }
    @PostMapping("/update/{number}/{time}/{day}/{id}")
    public String updateSchedule(@PathVariable("id") Long id,@PathVariable("number") String number,
                                 @PathVariable("time") int time, @PathVariable("day") int day, Schedule schedule,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            schedule.setId(id);
            return "update-schedule-group";
        }

        scheduleService.update(schedule);
        return "redirect:/ui/schedule/get/"+number;
    }
    @GetMapping("/update/{number}/{time}/{day}/")
    public String showCreateForm(@PathVariable("number") String number,@PathVariable("time")
            int time, @PathVariable("day") int day, Model model) {
        Group group = groupService.getByNumber(number);
        Schedule schedule = new Schedule();
        schedule.setId(null);
        schedule.setTime(time);
        schedule.setDay(day);
        schedule.setGroup(group);
        List<Teaching> teachings = teachingService.getAll();
        model.addAttribute("schedule",schedule);
        model.addAttribute("teachings", teachings);
        return "create-schedule-group";
    }
    @PostMapping("/update/{number}/{time}/{day}/")
    public String createSchedule(@PathVariable("number") String number,
                                 @PathVariable("time") int time, @PathVariable("day") int day, Schedule schedule,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-schedule-group";
        }

        scheduleService.create(schedule);
        return "redirect:/ui/schedule/get/"+number;
    }
    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable("id") Long id, Schedule schedule,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            schedule.setId(id);
            return "update-schedule";
        }

        scheduleService.update(schedule);
        return "redirect:/ui/schedule/get/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable("id") Long id, Model model) {
        Schedule schedule = scheduleService.getById(id);
        if(schedule == null)
            return ("Invalid schedule Id:"+id);
        scheduleService.delete(id);
        return "redirect:/ui/schedule/get/all";
    }
    @GetMapping("/delete/{number}/{time}/{day}/{id}")
    public String deleteScheduleGroup(@PathVariable("id") Long id,@PathVariable("number") String number,
                                      @PathVariable("time") int time, @PathVariable("day") int day, Model model) {
        Schedule schedule = scheduleService.getById(id);
        if(schedule == null)
            return ("Invalid schedule Id:"+id);
        scheduleService.delete(id);
        return "redirect:/ui/schedule/get/"+number;
    }
}
