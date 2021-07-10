package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.EmailData;
import com.trasportManagement.transportservice.model.Login;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.EmailSenderService;
import com.trasportManagement.transportservice.service.LoginService;
import com.trasportManagement.transportservice.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private LoginService loginService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody(required=true) EmailData e) {
        emailSenderService.sendSimpleEmail(e.getToEmail(), e.getBody(), e.getSubject());
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/forgot-password/send-email/{userName}")
    public ResponseEntity<String> sendOtp(@PathVariable String userName, @RequestBody(required=true) EmailData e) {

        final UserDetails userDetails
                = loginService.loadUserByUsername(userName);

        Login login = (Login) userDetails;

        int otp = otpService.generateOTP(userName);

        emailSenderService.sendEmailWithTemplate(e);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/forgot-password/validate-otp/{userName}/{validateOtp}")
    public ResponseEntity<Result<String>> validateOtp(@PathVariable String userName, @PathVariable int validateOtp) {

        Result<String> response = new Result("Failure");

        final UserDetails userDetails
                = loginService.loadUserByUsername(userName);

        Login login = (Login) userDetails;

        int otp = otpService.getOtp(userName);

        otpService.clearOTP(userName);

        if (validateOtp == otp) {
            response.setMessage("Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot-password/send-email")
    public ResponseEntity<String> forgotPasswordEmail(@RequestBody(required=true) String userName) {

        final UserDetails userDetails
                = loginService.loadUserByUsername(userName);

        Login login = (Login) userDetails;

        int otp = otpService.generateOTP(userName);

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("userName", userName);

        String toEmail = login.getCustomLogin().getEmail();
        String subject = "Forgot Password";

        EmailData e = new EmailData(toEmail, subject, model);

        emailSenderService.sendEmailWithTemplate(e);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}