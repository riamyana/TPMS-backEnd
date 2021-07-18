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
        final String SQL = "SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,gender,mobileNo,dob, profileImage, requestDate, status, description FROM Member as m INNER JOIN MemberType as mt ON m.memberTypeId=mt.memberTypeId";
        List<MemberWithMemberType> memberList = jdbcTemplate.query(SQL, new MemberWithMemberTypeRowMapper());
        return memberList;
    }

    @Override
    public List<MemberWithMemberType> findMemberById(int memberId) {
        final String SQL = "SELECT memberId,m.memberTypeId as membertypeid,memberTypeName,firstName,lastName,gender,mobileNo,dob, profileImage, requestDate, status, description FROM Member as m INNER JOIN MemberType as mt ON " +
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
                        rs.getDate("dob"),
                        rs.getString("profileImage"),
                        rs.getDate("requestDate"),
                        rs.getInt("status"),
                        rs.getString("description")
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
                        rs.getDate("dob"),
                        rs.getString("profileImage"),
                        rs.getDate("requestDate"),
                        rs.getInt("status"),
                        rs.getString("description")

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
        final String SQL = "INSERT INTO Member (userId, memberTypeId, firstName, lastName, gender, mobileNo, dob, profileImage, requestDate)" +
                " VALUES (:userId, :memberTypeId, :firstName, :lastName, :gender, :mobileNo, :dob, :profileImage, :requestDate)";

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m), holder);

        final int id = holder.getKey().intValue();
        return id;
    }

    @Override
    public int updateMember(int memberId, Member m) {
        m.setMemberId(memberId);
        final String SQL = "UPDATE Member SET memberTypeId=:memberTypeId, firstName=:firstName, lastName=:lastName, gender=:gender, " +
                "mobileNo=:mobileNo, dob=:dob, status=:status, profileImage=:profileImage WHERE userId=:userId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m));
    }

    @Override
    public boolean deleteMember(int memberId) {
        Member m = new Member();
        m.setMemberId(memberId);
        final String SQL = "DELETE FROM Member WHERE memberId=:memberId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(m)) > 0;
    }

    @Override
    public List<Member> findMembersWithPassRequest() {
        final String SQL = "SELECT m.*, l.userName FROM Member as m, Login as l WHERE l.id=m.userId and (status=0 or status=2 or status=3)";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new Member(
                        rs.getInt("memberId"),
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getInt("memberTypeId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("gender"),
                        rs.getString("mobileNo"),
                        rs.getDate("dob"),
                        rs.getString("profileImage"),
                        rs.getDate("requestDate"),
                        rs.getInt("status"),
                        rs.getString("description")
                )
        );
    }

    @Override
    public int changePassRequestStatus(int memberId, int status, String description) {
        final String SQL = "UPDATE Member SET status=:status, description=:description WHERE memberId=:memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("status", status)
                .addValue("description", description);
        return jdbcTemplate.update(SQL, parameters);
    }

    @Override
    public int coutsOfStatus(int status) {
        final String SQL = "SELECT count(*) FROM Member WHERE STATUS=:status";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("status", status);

        int count = jdbcTemplate.queryForObject(SQL, parameters, Integer.class);
        return count;
    }

}
