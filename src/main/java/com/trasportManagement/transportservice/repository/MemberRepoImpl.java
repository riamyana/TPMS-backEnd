package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.repository.mapper.MemberWithMemberTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "memberRepo")
public class MemberRepoImpl implements MemberRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<MemberWithMemberType> findAllMembers() {
        final String SQL = "SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,mobileNo,dob FROM Member as m INNER JOIN MemberType as mt ON m.memberTypeId=mt.memberTypeId";
        List<MemberWithMemberType> memberList = jdbcTemplate.query(SQL, new MemberWithMemberTypeRowMapper());
        return memberList;
    }


    //single data
    @Override
    public List<MemberWithMemberType> findMemberById(int memberId) {
        final String SQL = "SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,mobileNo,dob FROM Member as m INNER JOIN MemberType as mt ON " +
                "m.memberTypeId=mt.memberTypeId WHERE memberId= :memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId);

        List<MemberWithMemberType> memberList = jdbcTemplate.queryForList(SQL, parameters, MemberWithMemberType.class);
        return memberList;
    }

    @Override
    public int addMember(Member m) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Member (memberTypeId, firstName, lastName, mobileNo, dob) VALUES (:memberTypeId, :firstName, :lastName, :mobileNo, :dob)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m), holder);

    }

    @Override
    public int updateMember(int memberId, Member m) {
        m.setMemberId(memberId);
        final String SQL = "UPDATE Member SET memberTypeId=:memberTypeId, firstName=:firstName, lastName=:lastName, mobileNo=:mobileNo, dob=:dob WHERE memberId=:memberId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m));
    }

    @Override
    public boolean deleteMember(int memberId) {
        Member m = new Member();
        m.setMemberId(memberId);
        final String SQL = "DELETE FROM Member WHERE memberId=:memberId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m)) > 0;
    }

}
