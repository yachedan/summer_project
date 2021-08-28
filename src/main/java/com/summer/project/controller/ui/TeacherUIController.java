package com.summer.project.controller.ui;

import com.summer.project.model.Department;
import com.summer.project.model.Teacher;
import com.summer.project.service.DepartmentService;
import com.summer.project.service.TeacherService;
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
    @class TeacherUIController
    @version 1.0.0
    @since 8/24/2021 - 21.01
*/
@Controller
@RequestMapping("ui/teacher")
public class TeacherUIController {
    private final TeacherService teacherService;
    private final DepartmentService departmentService;

    public TeacherUIController(TeacherService teacherService, DepartmentService departmentService) {
        this.teacherService = teacherService;
        this.departmentService = departmentService;
    }

    @GetMapping("/get/all")
    public String getTeachers(HttpServletRequest request, Model model) {
        int page = 0;
        int size = 10;
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("teachers", teacherService.getAll(PageRequest.of(page, size)));
        return "get-teacher";
    }

    @GetMapping("/signup")
    public String showSignupForm(Teacher teacher, Model model) {
        List<Department> departments = departmentService.getAll();
        model.addAttribute("teacher", teacher);
        model.addAttribute("departments", departments);
        return "create-teacher";
    }

    @PostMapping("/create")
    public String create(Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors())
            return "create-teacher";

        teacherService.create(teacher);
        return "redirect:/ui/teacher/get/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.getById(id);
        if (teacher == null)
            return ("Invalid teacher Id:" + id);
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments", departments);
        model.addAttribute("teacher", teacher);

        return "update-teacher";
    }

    @PostMapping("/update/{id}")
    public String updateSpec(@PathVariable("id") Long id, Teacher teacher,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            teacher.setId(id);
            return "update-teacher";
        }
        teacherService.update(teacher);
        return "redirect:/ui/teacher/get/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.getById(id);
        if (teacher == null)
            return ("Invalid teacher Id:" + id);
        teacherService.delete(id);
        return "redirect:/ui/teacher/get/all";
    }
}