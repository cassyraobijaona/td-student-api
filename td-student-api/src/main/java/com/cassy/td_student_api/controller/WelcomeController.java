package com.cassy.td_student_api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return "Welcome " + name;
    }
}