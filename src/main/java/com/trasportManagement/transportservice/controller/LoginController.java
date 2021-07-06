package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.*;
import com.trasportManagement.transportservice.service.LoginService;
import com.trasportManagement.transportservice.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;


    @GetMapping("/users/{id}")
    public ResponseEntity<List<RegistrationRequest>> findByUserId(@PathVariable int id) {
        List<RegistrationRequest> userList = loginService.findUserByUserId(id);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody(required=true) JWTRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Credentials", e);
        }

        final UserDetails userDetails
                = loginService.loadUserByUsername(jwtRequest.getUserName());

        final String token =
                jwtUtility.generateToken(userDetails);

//        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof CustomLogin) {
//            String username = ((CustomLogin)principal).getRole();
////            System.out.println(username+"treue");
//        } else {
//            String username = principal.toString();
//            System.out.println(username+"user");
//        }

        Login login = (Login) userDetails;
//        System.out.println(login.getCustomLogin().getRole());

//        System.out.println(SecurityContext.class);

        return  new JWTResponse(login.getCustomLogin().getId(),token, userDetails.getUsername(), login.getCustomLogin().getEmail(), login.getCustomLogin().getRole());
    }
}
