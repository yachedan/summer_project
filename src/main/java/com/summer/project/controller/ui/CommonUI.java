package com.summer.project.controller.ui;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    @author user
    @project summer_project
    @class CommonUI
    @version 1.0.0
    @since 8/21/2021 - 17.19
*/
@Hidden
@Controller
public class CommonUI implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("index");
    }

    /*@RequestMapping("ui/query")
    public String getQuery() {
        return "query";
    }*/

}
