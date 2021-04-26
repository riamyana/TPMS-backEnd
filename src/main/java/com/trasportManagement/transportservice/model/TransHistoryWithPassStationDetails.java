package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransHistoryWithPassStationDetails {
    private int transHistoryId;
    private int passId;
    private Long serialNo;
    private String fromStationName;
    private String toStationName;
    private Timestamp fromDateTime;
    private Timestamp toDateTime;
}
