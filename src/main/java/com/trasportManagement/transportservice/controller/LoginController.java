package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.CustomLogin;
import com.trasportManagement.transportservice.model.JWTRequest;
import com.trasportManagement.transportservice.model.JWTResponse;
import com.trasportManagement.transportservice.model.Login;
import com.trasportManagement.transportservice.service.LoginService;
import com.trasportManagement.transportservice.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;


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
