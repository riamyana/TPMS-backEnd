package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.repository.mapper.MemberTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
        final String SQL = "SELECT * FROM MemberType";
        List<MemberType> memberTypeList=jdbcTemplate.query(SQL,new MemberTypeRowMapper());
        return memberTypeList;
    }

    @Override
    public List<MemberType> findMemberTypeById(int memberTypeId) {
        final String SQL = "SELECT * FROM MemberType WHERE memberTypeId = :memberTypeId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberTypeId", memberTypeId);

        List<MemberType> memberTypeList=jdbcTemplate.query(SQL, parameters, new MemberTypeRowMapper());
        return memberTypeList;
    }

    @Override
    public int addMemberType(MemberType m) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO MemberType (memberTypeName) VALUES (:memberTypeName)";
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m), holder);
        m.setMemberTypeId(holder.getKey().intValue());
        final int id = holder.getKey().intValue();
        return id;
    }

    @Override
    public int updateMemberType(int memberTypeId, MemberType m) {
        m.setMemberTypeId(memberTypeId);
        final String SQL = "UPDATE MemberType SET memberTypeName=:memberTypeName WHERE memberTypeId=:memberTypeId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m));
    }

    @Override
    public boolean deleteMemberType(int memberTypeId) {
        MemberType m = new MemberType();
        m.setMemberTypeId(memberTypeId);
        final String SQL = "DELETE FROM MemberType WHERE memberTypeId=:memberTypeId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m)) > 0;
    }

}
