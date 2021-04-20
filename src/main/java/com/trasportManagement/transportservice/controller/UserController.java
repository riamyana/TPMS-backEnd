package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.ChangePasswordRequest;
import com.trasportManagement.transportservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/home")
    public String user(){
        return "user";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody ChangePasswordRequest data){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        String username = userDetails.getUsername();
//        return username;
        return loginService.changePassword(data);
    }
}
