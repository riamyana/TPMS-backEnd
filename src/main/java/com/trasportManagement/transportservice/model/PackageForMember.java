package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageForMember {
    private int id;
    private String name;
    private String transportMode;
    private String subscriptionType;
    private int counts;
    private int validity;
    private int balance;
    private int price;
    private int memberPackgeId;
    private Date discountStartDate;
    private Date discountEndDate;
    private int discountPercentage;
    private String discountDescription;
}
