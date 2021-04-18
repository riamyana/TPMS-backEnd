package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Login;
import com.trasportManagement.transportservice.model.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final LoginService loginService;
    private final EmailValidatorService emailValidatorService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidatorService.test(request.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }

        return loginService.signUpUser(
                new Login(
                        request.getUserName(),
                        request.getPassword(),
                        request.getEmail(),
                        "USER"
                )
        );
    }
}
