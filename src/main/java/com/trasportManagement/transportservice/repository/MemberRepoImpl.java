package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithAddress;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.repository.mapper.MemberWithMemberTypeRowMapper;
import com.trasportManagement.transportservice.repository.resultSetExtractor.AddressResultSetExtractor;
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
        final String SQL = "SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,gender,mobileNo,dob FROM Member as m INNER JOIN MemberType as mt ON m.memberTypeId=mt.memberTypeId";
        List<MemberWithMemberType> memberList = jdbcTemplate.query(SQL, new MemberWithMemberTypeRowMapper());
        return memberList;
    }

    @Override
    public List<MemberWithMemberType> findMemberById(int memberId) {
        final String SQL = "SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,gender,mobileNo,dob FROM Member as m INNER JOIN MemberType as mt ON " +
                "m.memberTypeId=mt.memberTypeId WHERE memberId= :memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId);

        List<MemberWithMemberType> memberList = jdbcTemplate.query(SQL, parameters, new MemberWithMemberTypeRowMapper());
        return memberList;
    }

    @Override
    public List<Member> findMemberByUserId(int userId) {
        final String SQL = "SELECT * from Member WHERE userId= :userId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);

        List<Member> memberList = jdbcTemplate.query(SQL, parameters, (rs, i) ->
                new Member(
                        rs.getInt("memberId"),
                        rs.getInt("userId"),
                        rs.getInt("memberTypeId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getString("mobileNo"),
                        rs.getDate("dob")
                )
        );
        return memberList;
    }

    @Override
    public List<Member> findMembers() {
        final String SQL = "SELECT * FROM Member";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new Member(
                        rs.getInt("memberId"),
                        rs.getInt("userId"),
                        rs.getInt("memberTypeId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getString("mobileNo"),
                        rs.getDate("dob")
                )
        );
    }

    @Override
    public List<MemberWithAddress> findMemberWithAddress() {
        final String SQL= "SELECT m.memberId, memberTypeId, firstName, lastName, gender, mobileNo, dob , addressId, addLine1, addLine2, city, zipCode " +
                "FROM Member as m INNER JOIN Address as a ON m.memberId = a.memberId";

        List<MemberWithAddress> memberList = jdbcTemplate.query(SQL, new AddressResultSetExtractor());
        return memberList;
    }



    @Override
    public int addMember(Member m) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Member (userId, memberTypeId, firstName, lastName, gender, mobileNo, dob) VALUES (:memberTypeId, :firstName, :lastName, :gender, :mobileNo, :dob)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m), holder);
    }

    @Override
    public int updateMember(int memberId, Member m) {
        m.setMemberId(memberId);
        final String SQL = "UPDATE Member SET memberTypeId=:memberTypeId, firstName=:firstName, lastName=:lastName, gender=:gender, mobileNo=:mobileNo, dob=:dob WHERE memberId=:memberId";
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
