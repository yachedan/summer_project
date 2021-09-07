package com.summer.project.controller.ui;

import com.summer.project.model.*;
import com.summer.project.service.ConductService;
import com.summer.project.service.GroupService;
import com.summer.project.service.ScheduleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
    @author user
    @project summer_project
    @class ConductUIController
    @version 1.0.0
    @since 8/31/2021 - 19.21
*/
@Controller
@RequestMapping("ui/conduct")
public class ConductUIController {
    private final ConductService conductService;
    private final ScheduleService scheduleService;
    private final GroupService groupService;

    public ConductUIController(ConductService conductService, ScheduleService scheduleService,
                               GroupService groupService){
        this.conductService = conductService;
        this.scheduleService = scheduleService;
        this.groupService = groupService;
    }
    @GetMapping("/get/all")
    public String getConduct(HttpServletRequest request, Model model){
        /*int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("entries", conductService.getAll(PageRequest.of(page,size)));*/
        model.addAttribute("groups", groupService.getAll());
        return "get-conduct";
    }
    @GetMapping("/get/{groupId}")
    public String getEntriesByGroup(@PathVariable("groupId")Long groupId, HttpServletRequest request, Model model){
        List<Schedule> entries = scheduleService.getByGroup(groupId);
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
        return "get-conduct-group";
    }
    @GetMapping("/table/{groupId}/{time}/{day}/{id}")
    public String showTable(@PathVariable("id") Long id,@PathVariable("groupId") Long groupId,@PathVariable("time")
            int time, @PathVariable("day") int day, HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("entries", conductService.getAllBySchedule(id, PageRequest.of(page,size,
                Sort.by("date"))));
        return "table-conduct";
    }
    @GetMapping("/table/{groupId}/{time}/{day}/{scheduleId}/signup")
    public String showSignupForm(@PathVariable("scheduleId") Long scheduleId,@PathVariable("groupId") Long groupId,@PathVariable("time")
            int time, @PathVariable("day") int day, Conduct conduct, Model model){
        conduct.setId(null);
        model.addAttribute("conduct", conduct);
        model.addAttribute("schedule", scheduleService.getById(scheduleId));
        return "create-conduct";
    }
    @PostMapping("/table/{groupId}/{time}/{day}/{scheduleId}/create")
    public String create(@PathVariable("scheduleId") Long scheduleId,@PathVariable("groupId") Long groupId,@PathVariable("time")
            int time, @PathVariable("day") int day, Conduct conduct, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-conduct";

        conductService.create(conduct);
        return "redirect:/ui/conduct/table/"+groupId+"/"+time+"/"+day+"/"+scheduleId;
    }
    @GetMapping("/table/{groupId}/{time}/{day}/{scheduleId}/update/{conductId}")
    public String showUpdateForm(@PathVariable("scheduleId") Long scheduleId,@PathVariable("groupId") Long groupId,@PathVariable("time")
            int time, @PathVariable("day") int day,@PathVariable("conductId") Long conductId, Model model){
        Conduct conduct = conductService.getById(conductId);
        if(conduct == null)
            return ("Invalid conduct id:"+conductId);
        model.addAttribute("conduct", conduct);
        model.addAttribute("schedule", scheduleService.getById(scheduleId));
        return  "update-conduct";
    }

    @PostMapping("/table/{groupId}/{time}/{day}/{scheduleId}/update/{conductId}")
    public String updateConduct(@PathVariable("scheduleId") Long scheduleId,@PathVariable("groupId") Long groupId,@PathVariable("time")
            int time, @PathVariable("day") int day,@PathVariable("conductId") Long conductId,
                                   Conduct conduct, BindingResult result, Model model){
        conduct.setId(conductId);
        if (result.hasErrors()) {
            return "update-conduct";
        }

        conductService.update(conduct);
        return "redirect:/ui/conduct/table/"+groupId+"/"+time+"/"+day+"/"+scheduleId;
    }
    @GetMapping("/table/{groupId}/{time}/{day}/{id}/delete/{conductId}")
    public String deleteConduct(@PathVariable("id") Long id,@PathVariable("groupId") Long groupId,@PathVariable("time")
            int time, @PathVariable("day") int day,@PathVariable("conductId") Long conductId, Model model) {
        Conduct conduct = conductService.getById(conductId);
        if(conduct == null)
            return ("Invalid conduct Id:"+conductId);
        conductService.delete(conductId);
        return "redirect:/ui/conduct/table/"+groupId+"/"+time+"/"+day+"/"+id;
    }
}
