package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import com.trasportManagement.transportservice.repository.mapper.MemberWithMemberTypeRowMapper;
import com.trasportManagement.transportservice.repository.mapper.ProofWithMemberTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        String sql="SELECT proofId,proofName,p.memberTypeId as membertypeid,mt.memberTypeName as membertypename FROM Proof as p INNER JOIN MemberType as mt ON p.memberTypeId=mt.memberTypeId";
        List<ProofWithMemberType> proofList = jdbcTemplate.query(sql, new ProofWithMemberTypeRowMapper());
        return proofList;
    }

    @Override
    public int addProof(Proof p) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Proof (proofName,memberTypeId) VALUES (:proofName,:memberTypeId)";
        int n =jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(p), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else {
            return 0;
        }
    }

    @Override
    public int updateProof(int proofId, Proof p) {
        p.setProofId(proofId);
        String sql = "UPDATE Proof SET proofName=:proofName, memberTypeId=:memberTypeId WHERE proofId=:proofId";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(p));
        return n;
    }

    @Override
    public int deleteProof(int proofId) {
        Proof p = new Proof();
        p.setProofId(proofId);
        String sql = "DELETE FROM Proof WHERE proofId=:proofId";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(p));
    }
}
