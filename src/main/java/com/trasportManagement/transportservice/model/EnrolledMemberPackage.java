package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrolledMemberPackage {
    private int passId;
    private int packageId;
    private Date start;
    private Date end;
    private Double amount;
    private String name;
    private String subscriptionType;
}
