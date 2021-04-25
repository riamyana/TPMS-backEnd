package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Discount {
    private int id;
    private int packageId;
    private Date startDate;
    private Date endDate;
    private int percentage;
    private String description;
}
