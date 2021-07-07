package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.Proof;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProofMapper implements RowMapper<Proof> {
    @Override
    public Proof mapRow(ResultSet rs, int rowNum) throws SQLException {
        Proof p = new Proof();
        p.setProofId(rs.getInt("proofId"));
        p.setProofName(rs.getString("proofName"));
        return p;
    }
}
