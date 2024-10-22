package com.hariti.asmaa.FranceTour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Will look for /WEB-INF/views/home.jsp
    }
}