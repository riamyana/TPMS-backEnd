package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberTypePackage {
    private int id;
    private int packageId;
    private int memberTypeId;
    private Date discountStartDate;
    private Date discountEndDate;
    private int discountPercentage;
    private String discountDescription;
}
