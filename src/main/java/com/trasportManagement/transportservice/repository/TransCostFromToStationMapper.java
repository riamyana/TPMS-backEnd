package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransCostFromToStation;
import com.trasportManagement.transportservice.model.TransCostWithStationDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransCostFromToStationMapper implements RowMapper<TransCostFromToStation> {
    @Override
    public TransCostFromToStation mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransCostFromToStation t = new TransCostFromToStation();
        t.setCost(rs.getDouble("cost"));
        return t;
    }
}
