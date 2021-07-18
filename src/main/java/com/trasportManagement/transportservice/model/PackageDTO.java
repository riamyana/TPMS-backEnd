package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageDTO {
    private int id;
    private String name;
    private String transportMode;
    private String subscriptionType;
    private int counts;
    private int validity;
    private int balance;
    private int price;
    private String memberTypeName;
}
