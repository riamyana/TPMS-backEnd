package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.repository.mapper.MemberTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "memberTypeRepo")
public class MemberTypeRepoImpl implements MemberTypeRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<MemberType> findAllMemberTypes() {
        String sql = "SELECT * FROM MemberType";
        List<MemberType> memberTypeList=jdbcTemplate.query(sql,new MemberTypeRowMapper());
        return memberTypeList;
    }

    @Override
    public List<MemberType> findMemberTypeById(int memberTypeId) {
        String sql = "SELECT * FROM MemberType WHERE memberTypeId = " +memberTypeId;
        List<MemberType> memberTypeList=jdbcTemplate.query(sql,new MemberTypeRowMapper());
        return memberTypeList;
    }

    @Override
    public int addMemberType(MemberType m) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO MemberType (memberTypeName) VALUES (:memberTypeName)";
        int n =jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(m), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else {
            return 0;
        }
    }

    @Override
    public int updateMemberType(int memberTypeId, MemberType m) {
        m.setMemberTypeId(memberTypeId);
        String sql = "UPDATE MemberType SET memberTypeName=:memberTypeName WHERE memberTypeId=:memberTypeId";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(m));
        return n;
    }

    @Override
    public int deleteMemberType(int memberTypeId) {
        MemberType m = new MemberType();
        m.setMemberTypeId(memberTypeId);
        String sql = "DELETE FROM MemberType WHERE memberTypeId=:memberTypeId";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(m));
    }
}
