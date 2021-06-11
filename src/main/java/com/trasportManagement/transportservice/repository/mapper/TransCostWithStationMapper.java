package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.TransCostWithStationDetails;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransCostWithStationMapper implements RowMapper<TransCostWithStationDetails> {
    @Override
    public TransCostWithStationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransCostWithStationDetails t = new TransCostWithStationDetails();
        t.setId(rs.getInt("transCostId"));
        t.setFromStationName(rs.getString("fromStationName"));
        t.setToStationName(rs.getString("toStationName"));
        t.setCost(rs.getDouble("cost"));
        return t;
    }
}
