package com.trasportManagement.transportservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForgotPassword {
    private String userName;
    private int otp;
    private String newPassword;
}

