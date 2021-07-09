package com.trasportManagement.transportservice.model;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class MemberProofsWithMemberDetails extends MemberProof{
    private String firstName;
    private String lastName;
    private String mobileNo;
    private Date dob;
    private String gender;
}
