package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address a = new Address();
        a.setAddressId(rs.getInt("addressId"));
        a.setMemberId(rs.getInt("memberId"));
        a.setAddLine1(rs.getString("addLine1"));
        a.setAddLine2(rs.getString("addLine2"));
        a.setCity(rs.getString("city"));
        a.setZipCode(rs.getString("zipCode"));
        return a;
    }
}
