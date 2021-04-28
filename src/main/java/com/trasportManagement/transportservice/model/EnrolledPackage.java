package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrolledPackage {

    private int id;
    private int passId;
    private int packageId;
    private Date start;
    private Date end;
    private int isActive;
}
