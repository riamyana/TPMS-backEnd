package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.ProofRequirement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProofRequirementMapper implements RowMapper<ProofRequirement> {
    @Override
    public ProofRequirement mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProofRequirement p = new ProofRequirement();
        p.setId(rs.getInt("id"));
        p.setMemberTypeId(rs.getInt("memberTypeId"));
        p.setMemberTypeName(rs.getString("memberTypeName"));
        p.setProofId(rs.getInt("proofId"));
        p.setProofName(rs.getString("proofName"));
        return p;
    }
}
