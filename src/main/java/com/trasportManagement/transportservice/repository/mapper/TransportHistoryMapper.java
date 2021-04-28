package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.TransHistoryWithPassStationDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportHistoryMapper implements RowMapper<TransHistoryWithPassStationDetails> {
    @Override
    public TransHistoryWithPassStationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransHistoryWithPassStationDetails t = new TransHistoryWithPassStationDetails();
        t.setTransHistoryId(rs.getInt("transHistoryId"));
        t.setPassId(rs.getInt("passID"));
        t.setSerialNo(rs.getLong("serialNo"));
        t.setFromStationName(rs.getString("fromStationName"));
        t.setToStationName(rs.getString("toStationName"));
        t.setFromDateTime(rs.getTimestamp("fromDateTime"));
        t.setToDateTime(rs.getTimestamp("toDateTime"));
        return t;
    }
}
