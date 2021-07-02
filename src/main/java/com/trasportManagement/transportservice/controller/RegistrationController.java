package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.RegistrationRequest;
import com.trasportManagement.transportservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody(required=true) RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }
}
