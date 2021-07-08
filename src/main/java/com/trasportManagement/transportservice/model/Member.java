package com.trasportManagement.transportservice.model;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    private int memberId;
    private int userId;
    private int memberTypeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String mobileNo;
    private Date dob;
}
