package com.summer.project.controller.ui;

import com.summer.project.model.Subject;
import com.summer.project.model.Teacher;
import com.summer.project.model.Teaching;
import com.summer.project.service.SubjectService;
import com.summer.project.service.TeacherService;
import com.summer.project.service.TeachingService;
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
    @class TeachingUIController
    @version 1.0.0
    @since 8/25/2021 - 16.17
*/
@Controller
@RequestMapping("ui/teaching")
public class TeachingUIController {
    private final TeachingService teachingService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;

    public TeachingUIController(TeachingService teachingService, SubjectService subjectService, TeacherService teacherService){
        this.teachingService = teachingService;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
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
        model.addAttribute("entries",teachingService.getAll(PageRequest.of(page,size)));
        return "get-teaching";
    }
    @GetMapping("/signup")
    public String showSignupForm(Teaching teaching, Model model){
        List<Teacher> teachers = teacherService.getAll();
        List<Subject> subjects = subjectService.getAll();
        model.addAttribute("teaching",teaching);
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);
        return "create-teaching";
    }
    @PostMapping("/create")
    public String create(Teaching teaching, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-teaching";
        teachingService.create(teaching);
        return "redirect:/ui/teaching/get/all";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Teaching teaching = teachingService.getById(id);
        if(teaching == null)
            return ("Invalid teaching Id:"+id);
        List<Teacher> teachers = teacherService.getAll();
        List<Subject> subjects = subjectService.getAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teaching", teaching);

        return "update-teaching";
    }
    @PostMapping("/update/{id}")
    public String updateSpec(@PathVariable("id") Long id, Teaching teaching,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            teaching.setId(id);
            return "update-teaching";
        }

        teachingService.update(teaching);
        return "redirect:/ui/teaching/get/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id, Model model) {
        Teaching teaching = teachingService.getById(id);
        if(teaching == null)
            return ("Invalid teaching Id:"+id);
        teachingService.delete(id);
        return "redirect:/ui/teaching/get/all";
    }
}
