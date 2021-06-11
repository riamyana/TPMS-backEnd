package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.MemberTypeProof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository(value = "memberTypeProof")
public class MemberTypeProofRepoImpl implements MemberTypeProofRepo {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addMemberTypeProof(MemberTypeProof mp) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO MemberTypeProof (id, packageId, memberTypeId) VALUES (:id, :packageId, :memberTypeId)";

        int param = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(mp), holder);

        if (param == 0) {
            throw new TPMSCustomException("No record inserted of this Member Type Package.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        mp.setId(holder.getKey().intValue());
        return holder.getKey().intValue();
    }

    @Override
    public int updateMemberTypeProof(int id, MemberTypeProof mp) {
        mp.setId(id);
        final String SQL = "UPDATE MemberTypeProof SET packageId=:packageId, memberTypeId=:memberTypeId WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(mp));
    }

    @Override
    public boolean deleteMemberTypeProof(int id) {
        MemberTypeProof mp = new MemberTypeProof();
        mp.setId(id);
        final String SQL = "DELETE FROM MemberTypeProof WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(mp)) > 0;
    }
}
