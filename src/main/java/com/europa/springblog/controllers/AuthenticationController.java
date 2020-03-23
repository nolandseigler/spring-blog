package com.europa.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
//    //this is one way in curric and another in lecture.......
//    //read some docs. commenting this out for now
//    @PostMapping("/logout")
//    public String logout() {
//        return "users/login";
//    }
}
