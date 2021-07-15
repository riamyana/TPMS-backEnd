package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.MemberProofsWithMemberDetails;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberProofRowMapper implements RowMapper<MemberProofsWithMemberDetails> {
    @Override
    public MemberProofsWithMemberDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        MemberProofsWithMemberDetails m = new MemberProofsWithMemberDetails();
        m.setMemberId(rs.getInt("memberid"));
        m.setMemberTypeId(rs.getInt("memberTypeId"));
        m.setFirstName(rs.getString("firstName"));   // memberTypeName is the alias in select join query
        m.setLastName(rs.getString("lastName"));
        m.setDob(rs.getDate("dob"));
        m.setMobileNo(rs.getString("mobileNo"));
        m.setGender(rs.getString("gender"));
        m.setMemProofId(rs.getInt("memProofId"));
        m.setProofId(rs.getInt("proofId"));
        m.setProofImage("http://localhost:8080/image/"  + rs.getString("proofImage"));
        return m;
    }
}
