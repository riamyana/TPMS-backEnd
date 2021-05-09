package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import com.trasportManagement.transportservice.repository.mapper.ProofByMemberTypeIdRowMapper;
import com.trasportManagement.transportservice.repository.mapper.ProofWithMemberTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "proofRepo")
public class ProofRepoImpl implements ProofRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ProofWithMemberType> findAllProofs() {
        final String SQL = "SELECT proofId,proofName,p.memberTypeId as membertypeid,mt.memberTypeName as membertypename FROM Proof as p INNER JOIN MemberType as mt ON p.memberTypeId=mt.memberTypeId";
        List<ProofWithMemberType> proofList = jdbcTemplate.query(SQL, new ProofWithMemberTypeRowMapper());
        return proofList;
    }

    @Override
    public List<Proof> findProofsByMemberTypeId(int memberTypeId) {
        final String SQL = "SELECT proofId,proofName FROM Proof as p INNER JOIN MemberType as mt ON p.memberTypeId=mt.memberTypeId AND mt.memberTypeId=:memberTypeId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberTypeId", memberTypeId);

        List<Proof> proofList = jdbcTemplate.query(SQL, new ProofByMemberTypeIdRowMapper());
        return proofList;
    }


    @Override
    public int addProof(Proof p) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Proof (proofName,memberTypeId) VALUES (:proofName,:memberTypeId)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p), holder);

    }

    @Override
    public int updateProof(int proofId, Proof p) {
        p.setProofId(proofId);
        final String SQL = "UPDATE Proof SET proofName=:proofName, memberTypeId=:memberTypeId WHERE proofId=:proofId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p));
    }

    @Override
    public boolean deleteProof(int proofId) {
        Proof p = new Proof();
        p.setProofId(proofId);
        final String SQL = "DELETE FROM Proof WHERE proofId=:proofId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p)) > 0;
    }

}
