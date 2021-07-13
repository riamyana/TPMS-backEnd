package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.EmailData;
import com.trasportManagement.transportservice.model.ForgotPassword;
import com.trasportManagement.transportservice.model.Login;
import com.trasportManagement.transportservice.model.RequestStatus;
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

    private static final String tpms = "TPMS";

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private LoginService loginService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody(required=true) EmailData e) {
        emailSenderService.sendSimpleEmail(e);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/forgot-password/validate-otp/{userName}")
    public ResponseEntity<Result<String>> validateOtp(@PathVariable String userName, @RequestBody(required=true) ForgotPassword data) {

        Result<String> response = new Result(201, "Failure");

        int otp = otpService.getOtp(userName);

        if (data.getOtp() == otp) {
            response.setMessage("Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot-password/send-email")
    public ResponseEntity<Result<String>> forgotPasswordEmail(@RequestBody(required=true) String userName) {

        String templateName = "forgot-password-email-template.flth";

        Result<String> response = new Result(201, "Success");

        final UserDetails userDetails
                = loginService.loadUserByUsername(userName);

        Login login = (Login) userDetails;

        int otp = otpService.generateOTP(userName);

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("userName", userName);
        model.put("tpms", tpms);
        model.put("otp", String.valueOf(otp));
        model.put("tpmsUrl", "http://localhost:4200/user/login");

        String toEmail = login.getCustomLogin().getEmail();
        String subject = "Forgot Password";

        EmailData e = new EmailData(toEmail, subject, model);

        emailSenderService.sendEmailWithTemplate(e, templateName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot-password/change-password")
    public ResponseEntity<Result<String>> changePassword(@RequestBody(required=true) ForgotPassword data) {

        Result<String> response = new Result(201, "Failure");

        int otp = otpService.getOtp(data.getUserName());

        if (data.getOtp() == otp) {
            loginService.changeForgotPassword(data);
            response.setMessage("Success");
            otpService.clearOTP(data.getUserName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pass-request-email")
    public ResponseEntity<Result<String>> passRequestEmail(@RequestBody(required=true) String userName) {

        String templateName = "pass-request-sent-email-template.flth";

        Result<String> response = new Result(201, "Success");

        final UserDetails userDetails
                = loginService.loadUserByUsername(userName);

        Login login = (Login) userDetails;

        Map<String, Object> model = new HashMap<String, Object>();

        model.put("userName", userName);
        model.put("tpms", tpms);
        model.put("tpmsUrl", "http://localhost:4200/user/login");

        String toEmail = login.getCustomLogin().getEmail();
        String subject = "Pass Request Placed Successfully";

        EmailData e = new EmailData(toEmail, subject, model);

        emailSenderService.sendEmailWithTemplate(e, templateName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pass-status-email")
    public ResponseEntity<Result<String>> passStatusEmail(@RequestBody(required=true) RequestStatus requestStatus) {
        Result<String> response = new Result(201, "Success");
        String status = "";
        String description = "We will send you your pass soon.";
        String templateName = "pass-status-email-template.flth";

        final UserDetails userDetails
                = loginService.loadUserByUsername(requestStatus.getUserName());

        Login login = (Login) userDetails;

        if (requestStatus.getStatus() == 1) {
            status = "Approved";
        } else if (requestStatus.getStatus() == 2) {
            status = "Not Approved";
            description = requestStatus.getDescription();
        }

        Map<String, Object> model = new HashMap<>();

        model.put("userName", requestStatus.getUserName());
        model.put("tpms", tpms);
        model.put("tpmsUrl", "http://localhost:4200/user/login");
        model.put("status", status);
        model.put("description", description);

        String toEmail = login.getCustomLogin().getEmail();
        String subject = "Pass Request Status";

        EmailData e = new EmailData(toEmail, subject, model);

        emailSenderService.sendEmailWithTemplate(e, templateName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}