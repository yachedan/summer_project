package com.summer.project.controller.ui;

import com.summer.project.model.Group;
import com.summer.project.model.Speciality;
import com.summer.project.service.GroupService;
import com.summer.project.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/*
    @author user
    @project summer_project
    @class GroupUIController
    @version 1.0.0
    @since 8/24/2021 - 12.45
*/
@Controller
@RequestMapping("ui/group")
public class GroupUIController {
    private final GroupService groupService;
    private final SpecialityService specialityService;

    public GroupUIController(GroupService groupService, SpecialityService specialityService){
        this.groupService = groupService;
        this.specialityService = specialityService;
    }

    @GetMapping("/get/all")
    public String getGroups(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("groups", groupService.getAll(PageRequest.of(page,size)));
        return "get-group";
    }
    @GetMapping("/signup")
    public  String showSignupForm(Group group, Model model){
        List<Speciality> specs = specialityService.getAll();
        model.addAttribute("group",group);
        model.addAttribute("specs",specs);
        return "create-group";
    }

    @PostMapping("/create")
    public String create(@Valid Group group, BindingResult result, Model model){
        var g = groupService.getByNumber(group.getNumber());
        if(g != null) {
            result.rejectValue("number", "error.number", "Number must be unique!");
            return "create-group";
        }
        groupService.create(group);
        return "redirect:/ui/group/get/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getById(id);
        if(group == null)
            return ("Invalid user Id:"+id);
        List<Speciality> specs = specialityService.getAll();
        model.addAttribute("specs",specs);
        model.addAttribute("group", group);

        return "update-group";
    }
    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable("id") Long id, Group group,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            group.setId(id);
            return "update-group";
        }
        groupService.update(group);
        return "redirect:/ui/group/get/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getById(id);
        if(group == null)
            return ("Invalid user Id:"+id);
        groupService.delete(id);
        return "redirect:/ui/group/get/all";
    }
}
