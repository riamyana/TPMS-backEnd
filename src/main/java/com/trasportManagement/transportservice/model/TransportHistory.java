package com.trasportManagement.transportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransportHistory {
    private int transHistoryId;
    private int passId;
    private int fromStationId;
    private int toStationId;
    private Timestamp fromDateTime;
    private Timestamp toDateTime;
}
