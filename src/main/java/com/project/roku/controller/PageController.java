package com.project.roku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    // add a request mapping for the head doctor
    @GetMapping("/prescriptions")
    public String prescriptionManagement(){
        return "prescription-management/prescription-management";
    }

    @GetMapping("/systems")
    public String showSystems(){
        return "systems";
    }

}
