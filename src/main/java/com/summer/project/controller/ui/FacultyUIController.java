package com.summer.project.controller.ui;

import com.summer.project.model.Faculty;
import com.summer.project.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityListeners;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/*
    @author user
    @project summer_project
    @class FacultyUIController
    @version 1.0.0
    @since 8/23/2021 - 01.06
*/
@Controller
@RequestMapping("ui/faculty")
@EntityListeners(AuditingEntityListener.class)
public class FacultyUIController {
    private final FacultyService service;

    public FacultyUIController(FacultyService service){
        this.service = service;
    }

    @GetMapping("/get/all")
    public String getFaculties(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("faculties", service.getAll(PageRequest.of(page,size)));
        return "get-faculty";
    }
    @GetMapping("/signup")
    public String showSignupForm(Faculty faculty, Model model){
        model.addAttribute("faculty", faculty);
        return "create-faculty";
    }
    @PostMapping("/create")
    public String create(Faculty faculty, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-faculty";

        service.create(faculty);
        return "redirect:/ui/faculty/get/all";
    }
    @GetMapping("/update/{id}")
    public String showUpdateFom(@PathVariable("id") Long id, Model model){
        Faculty faculty = service.getById(id);
        if(faculty == null)
            return ("Invalid faculty id:" + id);
        model.addAttribute("faculty", faculty);
        return "update-faculty";
    }
    @PostMapping("/update/{id}")
    public String updateFaculty(@PathVariable("id") Long id, Model model,
                                Faculty faculty, BindingResult result){
        if(result.hasErrors()){
            return "update-faculty";
        }
        service.update(faculty);
        return "redirect:/ui/faculty/get/all";
    }
    @GetMapping("/delete/{id}")
    public  String deleteFaculty(@PathVariable("id") Long id, Model model){
        Faculty faculty = service.getById(id);
        if(faculty == null)
            return ("Invalid faculty id:"+id);
        service.delete(id);
        return "redirect:/ui/faculty/get/all";
    }
}
