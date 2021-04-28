package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Station {
    private int stationId;
    private String stationName;
    private String SwipeMachineId;
    private Double latitude;
    private Double longitude;

}
