package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.MemberType;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberTypeRowMapper implements RowMapper<MemberType> {

    @Override
    public MemberType mapRow(ResultSet rs, int rowNum) throws SQLException {
        MemberType m = new MemberType();
        m.setMemberTypeId(rs.getInt("memberTypeId"));
        m.setMemberTypeName(rs.getString("memberTypeName"));
        return m;
    }

}
