package com.trasportManagement.transportservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationRequest {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String role;
}
