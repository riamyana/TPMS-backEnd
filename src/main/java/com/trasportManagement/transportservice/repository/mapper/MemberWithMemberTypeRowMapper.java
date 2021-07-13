package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.MemberWithMemberType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberWithMemberTypeRowMapper implements RowMapper<MemberWithMemberType> {
    @Override
    public MemberWithMemberType mapRow(ResultSet rs, int rowNum) throws SQLException {

        MemberWithMemberType m = new MemberWithMemberType();
        m.setMemberId(rs.getInt("memberId"));
        m.setMemberTypeName(rs.getString("memberTypeName"));   // memberTypeName is the alias in select join query
        m.setMemberTypeId(rs.getInt("membertypeid"));
        m.setFirstName(rs.getString("firstName"));
        m.setLastName(rs.getString("lastName"));
        m.setGender(rs.getString("gender"));
        m.setMobileNo(rs.getString("mobileNo"));
        m.setDob(rs.getDate("dob"));
        m.setProfileImage(rs.getString("profileImage"));
        m.setRequestDate(rs.getDate("requestDate"));
        m.setStatus(rs.getInt("status"));
        m.setDescription(rs.getString("description"));
        return m;
    }
}
