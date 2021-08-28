package com.summer.project.controller.ui;

import com.summer.project.model.Department;
import com.summer.project.model.Speciality;
import com.summer.project.service.DepartmentService;
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
import java.time.LocalDateTime;
import java.util.List;

/*
    @author user
    @project summer_project
    @class SpecialityUIController
    @version 1.0.0
    @since 8/24/2021 - 12.19
*/
@Controller
@RequestMapping("ui/speciality")
public class SpecialityUIController {
    @Autowired
    SpecialityService specialityService;
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/get/all")
    public String getSpecs(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("specs",specialityService.getAll(PageRequest.of(page,size)));
        return "get-spec";
    }
    @GetMapping("/signup")
    public String showSignupForm(Speciality speciality, Model model){
        List<Department> departments = departmentService.getAll();
        model.addAttribute("spec",speciality);
        model.addAttribute("departments", departments);
        return "create-spec";
    }
    @PostMapping("/create")
    public String create(Speciality speciality, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-spec";
        specialityService.create(speciality);
        return "redirect:/ui/speciality/get/all";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Speciality spec = specialityService.getById(id);
        if(spec == null)
            return ("Invalid speciality Id:"+id);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments",departments);
        model.addAttribute("spec", spec);
        return "update-spec";
    }
    @PostMapping("/update/{id}")
    public String updateSpec(@PathVariable("id") Long id, Speciality spec,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            spec.setId(id);
            return "update-spec";
        }
        specialityService.update(spec);
        return "redirect:/ui/speciality/get/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id, Model model) {
        Speciality speciality = specialityService.getById(id);
        if(speciality == null)
            return ("Invalid speciality Id:"+id);
        specialityService.delete(id);
        return "redirect:/ui/speciality/get/all";
    }
}