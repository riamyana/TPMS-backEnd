package com.trasportManagement.transportservice.model;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    private int memberId;
    private int memberTypeId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private Date dob;
}
