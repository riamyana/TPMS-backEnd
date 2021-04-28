package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Package {

    private int id;
    private int memberType;
    private String name;
    private int subscriptionType;
    private int counts;
    private int validity;
    private int balance;
    private int price;
}
