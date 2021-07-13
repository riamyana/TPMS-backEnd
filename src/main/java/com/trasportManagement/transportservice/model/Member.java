package com.trasportManagement.transportservice.model;

import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    private int memberId;
    private int userId;
    private String userName;
    private int memberTypeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String mobileNo;
    private Date dob;
    private String profileImage;
    private Date requestDate;
    private int status;
    private String description;

    public Member(int memberId, int userId, int memberTypeId, String firstName, String lastName,
                  String gender, String mobileNo, Date dob, String profileImage, Date requestDate,
                  int status, String description) {
        this.memberId = memberId;
        this.userId = userId;
        this.memberTypeId = memberTypeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.dob = dob;
        this.profileImage = profileImage;
        this.requestDate = requestDate;
        this.status = status;
        this.description = description;
    }
}
