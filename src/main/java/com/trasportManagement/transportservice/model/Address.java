package com.trasportManagement.transportservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    private int addressId;
    private int memberId;
    private String addLine1;
    private String addLine2;
    private String city;
    private String zipCode;
}
