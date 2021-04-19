package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member m = new Member();
        m.setMemberTypeId(rs.getInt("memberTypeId"));
        m.setFirstName(rs.getString("firstName"));
        m.setLastName(rs.getString("lastName"));
        m.setMobileNo(rs.getString("mobileNo"));
        m.setDob(rs.getDate("dob"));
        return m;
    }
}
