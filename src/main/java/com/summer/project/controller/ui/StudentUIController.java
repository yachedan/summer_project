package com.summer.project.controller.ui;

import com.summer.project.model.Group;
import com.summer.project.model.Student;
import com.summer.project.service.GroupService;
import com.summer.project.service.StudentService;
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
import java.time.LocalDateTime;
import java.util.List;

/*
    @author user
    @project summer_project
    @class StudentUIController
    @version 1.0.0
    @since 8/24/2021 - 13.59
*/
@Controller
@RequestMapping("ui/student")
public class StudentUIController {
    private final StudentService studentService;
    private final GroupService groupService;

    public StudentUIController(StudentService studentService, GroupService groupService){
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/get/all")
    public String getStudents(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("students", studentService.getAll(PageRequest.of(page,size)));
        return "get-student";
    }

    @GetMapping("/signup")
    public  String showSignupForm(Student student, Model model){
        List<Group> groups = groupService.getAll();
        model.addAttribute("student", student);
        model.addAttribute("groups",groups);
        return "create-student";
    }

    @PostMapping("/create")
    public String create(@Valid Student student, BindingResult result, Model model){
        /*if(result.hasErrors())
            return "create-student";*/
        studentService.create(student);
        return "redirect:/ui/student/get/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getById(id);
        if(student == null)
            return ("Invalid student Id:"+id);
        List<Group> groups = groupService.getAll();
        model.addAttribute("groups",groups);
        model.addAttribute("student", student);
        return "update-student";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Student student,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }
        studentService.update(student);
        return "redirect:/ui/student/get/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getById(id);
        if(student == null)
            return ("Invalid student Id:"+id);
        studentService.delete(id);
        return "redirect:/ui/student/get/all";
    }

}
