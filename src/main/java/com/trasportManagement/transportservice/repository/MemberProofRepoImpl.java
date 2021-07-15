package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.MemberProof;
import com.trasportManagement.transportservice.model.MemberProofsWithMemberDetails;
import com.trasportManagement.transportservice.repository.mapper.MemberProofRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "memberProofRepo")
public class MemberProofRepoImpl implements MemberProofRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addMemberProof(MemberProof mp) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO MemberProof(proofId,memberId,proofImage) VALUES (:proofId, :memberId,:proofImage)";
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(mp), holder);

        final int id = holder.getKey().intValue();
        return id;

    }

    @Override
    public List<MemberProofsWithMemberDetails> findAllMembersProofDetails() {
        final String SQL = "SELECT mp.memberId as memberid,firstName,lastName,mobileNo,dob,gender, memProofId,proofId, proofImage FROM MemberProof as mp INNER JOIN Member as m ON mp.memberId=m.memberId";
        List<MemberProofsWithMemberDetails> memProofDetailList = jdbcTemplate.query(SQL, new MemberProofRowMapper());
        return memProofDetailList;
    }

    @Override
    public List<MemberProofsWithMemberDetails> findMemberProofById(int memberId) {
        final String SQL = "SELECT mp.memberId as memberid,memberTypeId,firstName,lastName,mobileNo,dob,gender, memProofId,proofId,proofImage FROM MemberProof as mp INNER JOIN Member as m ON mp.memberId=m.memberId AND mp.memberId=:memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId);

        List<MemberProofsWithMemberDetails> memProofList = jdbcTemplate.query(SQL, parameters, new MemberProofRowMapper());
        return memProofList;
    }
}
