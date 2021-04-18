package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.JWTRequest;
import com.trasportManagement.transportservice.model.JWTResponse;
import com.trasportManagement.transportservice.service.LoginService;
import com.trasportManagement.transportservice.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = loginService.loadUserByUsername(jwtRequest.getUserName());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new JWTResponse(token);
    }
//
//    @GetMapping("/user")
//    public String hello(){
//        return "user";
//    }
//
//    @GetMapping("/admin")
//    public String admin(){
//        return "admin";
//    }
}
