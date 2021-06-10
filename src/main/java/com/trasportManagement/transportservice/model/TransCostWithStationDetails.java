package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransCostWithStationDetails{
    private int id;
    private Double cost;
    private String fromStationName;
    private String toStationName;
}
