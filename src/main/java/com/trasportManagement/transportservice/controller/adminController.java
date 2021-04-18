package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.model.RegistrationRequest;
import com.trasportManagement.transportservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/home")
    public String admin(){
        return "admin";
    }
}
