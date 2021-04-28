package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransportCost {
    private int transCostId;
    private int fromStationId;
    private int toStationId;
    private Double cost;
}
