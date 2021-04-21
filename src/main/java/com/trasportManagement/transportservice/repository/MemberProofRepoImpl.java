package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.MemberProof;
import com.trasportManagement.transportservice.model.MemberProofsWithMemberDetails;
import com.trasportManagement.transportservice.repository.mapper.MemberProofRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        String sql = "INSERT INTO MemberProof(proofId,memberId,uidNo,proofImage) VALUES (:proofId, :memberId, :uidNo, :proofImage)";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(mp), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else{
            return 0;
        }
    }

    @Override
    public List<MemberProofsWithMemberDetails> findAllMembersProofDetails() {
        String sql="SELECT mp.memberId as memberid,firstName,lastName,mobileNo,dob,memProofId,proofId,uidNo,proofImage FROM MemberProof as mp INNER JOIN Member as m ON mp.memberId=m.memberId";
        List<MemberProofsWithMemberDetails> memProofDetailList = jdbcTemplate.query(sql, new MemberProofRowMapper());
        return memProofDetailList;
    }

    @Override
    public List<MemberProofsWithMemberDetails> findMemberProofById(int memberId) {
        String sql="SELECT mp.memberId as memberid,firstName,lastName,mobileNo,dob,memProofId,proofId,uidNo,proofImage FROM MemberProof as mp INNER JOIN Member as m ON mp.memberId=m.memberId AND mp.memberId="+memberId;
        List<MemberProofsWithMemberDetails> memProofList = jdbcTemplate.query(sql, new MemberProofRowMapper());
        return memProofList;
    }
}
