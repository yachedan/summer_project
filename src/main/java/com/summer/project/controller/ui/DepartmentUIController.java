package com.summer.project.controller.ui;

import com.summer.project.model.Department;
import com.summer.project.model.Faculty;
import com.summer.project.service.DepartmentService;
import com.summer.project.service.FacultyService;
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
    @class DepartmentUIController
    @version 1.0.0
    @since 8/23/2021 - 23.27
*/
@Controller
@RequestMapping("ui/department")
public class DepartmentUIController {
    private final DepartmentService departmentService;
    private final FacultyService facultyService;

    public DepartmentUIController(DepartmentService departmentService, FacultyService facultyService){
        this.departmentService = departmentService;
        this.facultyService = facultyService;
    }
    @GetMapping("/get/all")
    public String getDepartments(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()){
            page = Integer.parseInt(request.getParameter("page"))-1;
        }
        if(request.getParameter("size") != null && !request.getParameter("size").isEmpty()){
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("departments", departmentService.getAll(PageRequest.of(page,size)));
        return "get-department";
    }
    @GetMapping("/signup")
    public String showSignupForm(Department department, Model model){
        List<Faculty> faculties = facultyService.getAll();
        model.addAttribute("department", department);
        model.addAttribute("faculties", faculties);
        return "create-department";
    }
    @PostMapping("/create")
    public String create(Department department, BindingResult result, Model model){
        if(result.hasErrors())
            return "create-department";

        departmentService.create(department);
        return "redirect:/ui/department/get/all";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Department department = departmentService.getById(id);
        if(department == null)
            return ("Invalid department id:"+id);
        List<Faculty> faculties = facultyService.getAll();
        model.addAttribute("faculties", faculties);
        model.addAttribute("department", department);
        return  "update-department";
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable("id") Long id, Department department,
                                   BindingResult result, Model model){
        if (result.hasErrors()) {
            return "update-department";
        }

        departmentService.update(department);
        return "redirect:/ui/department/get/all";
    }
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getById(id);
        if(department == null)
            return ("Invalid department Id:"+id);
        departmentService.delete(id);
        return "redirect:/ui/department/get/all";
    }
}

