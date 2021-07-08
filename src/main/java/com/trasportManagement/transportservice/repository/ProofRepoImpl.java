package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofRequirement;
import com.trasportManagement.transportservice.repository.mapper.ProofByMemberTypeIdRowMapper;
import com.trasportManagement.transportservice.repository.mapper.ProofMapper;
import com.trasportManagement.transportservice.repository.mapper.ProofRequirementMapper;
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
    public List<Proof> findAllProofs() {
        final String SQL = "SELECT * FROM Proof";
        List<Proof> proofList = jdbcTemplate.query(SQL, new ProofMapper());
        return proofList;
    }

    @Override
    public List<Proof> findProofsByMemberTypeId(int memberTypeId) {
        final String SQL = "SELECT p.proofId, p.proofName FROM MemberTypeProof as mp, Proof as p WHERE " +
                "mp.memberTypeId = :memberTypeId and mp.proofId=p.proofId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberTypeId", memberTypeId);

        List<Proof> proofList = jdbcTemplate.query(SQL, parameters, new ProofByMemberTypeIdRowMapper());
        return proofList;
    }


    @Override
    public int addProof(Proof p) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Proof (proofName) VALUES (:proofName)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p), holder);

    }

    @Override
    public int updateProof(int proofId, Proof p) {
        p.setProofId(proofId);
        final String SQL = "UPDATE Proof SET proofName=:proofName WHERE proofId=:proofId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p));
    }

    @Override
    public boolean deleteProof(int proofId) {
        Proof p = new Proof();
        p.setProofId(proofId);
        final String SQL = "DELETE FROM Proof WHERE proofId=:proofId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p)) > 0;
    }

    @Override
    public int addProofRequirement(ProofRequirement p) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO MemberTypeProof (proofId, memberTypeId) VALUES (:proofId, :memberTypeId)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p), holder);
    }

    @Override
    public List<ProofRequirement> findAllProofRequirement() {
        final String SQL = "SELECT mp.id, m.memberTypeId, m.memberTypeName, p.proofId, p.proofName from MemberType as m, Proof as p, " +
                "MemberTypeProof as mp where m.memberTypeId = mp.memberTypeId and p.proofId = mp.proofId";
        List<ProofRequirement> proofList = jdbcTemplate.query(SQL, new ProofRequirementMapper());
        return proofList;
    }

    @Override
    public int updateProofRequirement(int id, ProofRequirement p) {
        p.setId(id);
        final String SQL = "UPDATE MemberTypeProof SET memberTypeId=:memberTypeId, proofId=:proofId WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p));
    }

    @Override
    public boolean deleteProofRequirement(int id) {
        ProofRequirement p = new ProofRequirement();
        p.setId(id);
        final String SQL = "DELETE FROM MemberTypeProof WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p)) > 0;
    }

}
