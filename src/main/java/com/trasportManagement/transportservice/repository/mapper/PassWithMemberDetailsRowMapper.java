package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassWithMemberDetailsRowMapper implements RowMapper<PassWithMemberDetails> {
    @Override
    public PassWithMemberDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

        PassWithMemberDetails p = new PassWithMemberDetails();
        p.setPassId(rs.getInt("id"));
        p.setMemberId(rs.getInt("memberId"));   // memberTypeName is the alias in select join query
        p.setSerialNo(rs.getLong("serialNo"));
        p.setFirstName(rs.getString("firstName"));
        p.setLastName(rs.getString("lastName"));
        p.setExpiry(rs.getDate("expiryDate"));
        return p;
    }
}
