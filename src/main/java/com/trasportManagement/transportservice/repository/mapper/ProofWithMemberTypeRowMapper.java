package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProofWithMemberTypeRowMapper implements RowMapper<ProofWithMemberType> {
    @Override
    public ProofWithMemberType mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProofWithMemberType p = new ProofWithMemberType();
        p.setMemberTypeId(rs.getInt("membertypeid"));
        p.setMemberTypeName(rs.getString("membertypename"));   // memberTypeName is the alias in select join query
        p.setProofId(rs.getInt("proofId"));
        p.setProofName(rs.getString("proofName"));
        return p;
    }
}
