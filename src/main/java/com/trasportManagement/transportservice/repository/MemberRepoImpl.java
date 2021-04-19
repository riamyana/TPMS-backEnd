package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.repository.mapper.MemberWithMemberTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        String sql="SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,mobileNo,dob FROM Member as m INNER JOIN MemberType as mt ON m.memberTypeId=mt.memberTypeId";
        List<MemberWithMemberType> memberList = jdbcTemplate.query(sql, new MemberWithMemberTypeRowMapper());
        return memberList;
    }

    @Override
    public List<MemberWithMemberType> findMemberById(int memberId) {
        String sql="SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,mobileNo,dob FROM Member as m INNER JOIN MemberType as mt ON " +
                "m.memberTypeId=mt.memberTypeId WHERE memberId= " + memberId;
        List<MemberWithMemberType> memberList = jdbcTemplate.query(sql, new MemberWithMemberTypeRowMapper());
        return memberList;
    }

    @Override
    public int addMember(Member m) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO Member (memberTypeId, firstName, lastName, mobileNo, dob) VALUES (:memberTypeId, :firstName, :lastName, :mobileNo, :dob)";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(m), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else{
            return 0;
        }
    }

    @Override
    public int updateMember(int memberId, Member m) {
        m.setMemberId(memberId);
        String sql = "UPDATE Member SET memberTypeId=:memberTypeId, firstName=:firstName, lastName=:lastName, mobileNo=:mobileNo, dob=:dob WHERE memberId=:memberId";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(m));
        return n;
    }

    @Override
    public int deleteMember(int memberId) {
        Member m = new Member();
        m.setMemberId(memberId);
        String sql = "DELETE FROM Member WHERE memberId=:memberId";
        return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(m));
    }
}
