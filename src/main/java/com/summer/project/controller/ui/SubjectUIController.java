package com.summer.project.controller.ui;

import com.summer.project.model.Department;
import com.summer.project.model.Subject;
import com.summer.project.service.DepartmentService;
import com.summer.project.service.SubjectService;
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
    @class SubjectUIController
    @version 1.0.0
    @since 8/24/2021 - 23.22
*/
@Controller
@RequestMapping("/ui/subject")
public class SubjectUIController {
    private final SubjectService subjectService;
    private final DepartmentService departmentService;

    public SubjectUIController(SubjectService subjectService, DepartmentService departmentService){
        this.subjectService = subjectService;
        this.departmentService = departmentService;
    }
    LocalDateTime created_at = LocalDateTime.MIN;

    @GetMapping("/get/all")
    public String getSubjects(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("subjects",subjectService.getAll(PageRequest.of(page,size)));
        return "get-subject";
    }
    @GetMapping("/signup")
    public String showSignupForm(Subject subject, Model model){
        List<Department> departments = departmentService.getAll();
        model.addAttribute("subject",subject);
        model.addAttribute("departments", departments);
        return "create-subject";
    }
    @PostMapping("/create")
    public String create(Subject subject, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-subject";
        subjectService.create(subject);
        return "redirect:/ui/subject/get/all";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Subject subject = subjectService.getById(id);
        if(subject == null)
            return ("Invalid subject Id:"+id);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments",departments);
        model.addAttribute("subject", subject);

        return "update-subject";
    }
    @PostMapping("/update/{id}")
    public String updateSpec(@PathVariable("id") Long id, Subject subject,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            subject.setId(id);
            return "update-subject";
        }

        subjectService.update(subject);
        return "redirect:/ui/subject/get/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id, Model model) {
        Subject subject = subjectService.getById(id);
        if(subject == null)
            return ("Invalid subject Id:"+id);
        subjectService.delete(id);
        return "redirect:/ui/subject/get/all";
    }
}
