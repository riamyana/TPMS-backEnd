package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.repository.mapper.PassWithMemberDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "passRepo")
public class PassRepoImpl implements PassRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<PassWithMemberDetails> findAllMemberPasses() {
        final String SQL = "SELECT id,p.memberId,serialNo,expiryDate,m.firstName as firstName,m.lastName as lastName FROM Pass as p INNER JOIN Member as m ON p.memberId=m.memberId";
        List<PassWithMemberDetails> passList = jdbcTemplate.query(SQL, new PassWithMemberDetailsRowMapper());
        return passList;
    }

    @Override
    public List<PassWithMemberDetails> findMemberPassesByMemberId(int memberId) {
        final String SQL = "SELECT id,p.memberId,serialNo,expiryDate,m.firstName as firstName,m.lastName as lastName FROM Pass as p INNER JOIN Member as m ON p.memberId=m.memberId and m.memberId=:memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId);
        List<PassWithMemberDetails> passList = jdbcTemplate.query(SQL, parameters, new PassWithMemberDetailsRowMapper());
        return passList;
    }

    @Override
    public List<Pass> findPasseByUserId(int userId) {
        final String SQL = "SELECT p.* FROM Pass as p, Member AS m WHERE m.memberId=p.memberId AND m.userId=:userId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);
        List<Pass> passList = jdbcTemplate.query(SQL, parameters, (rs, i) ->
                new Pass(
                        rs.getInt("id"),
                        rs.getInt("memberId"),
                        rs.getLong("serialNo"),
                        rs.getDate("expiryDate")
                ));
        return passList;
    }

    @Override
    public List<Pass> findAllPasses(){
        final String SQL = "SELECT * FROM Pass";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new Pass(
                        rs.getInt("id"),
                        rs.getInt("memberId"),
                        rs.getLong("serialNo"),
                        rs.getDate("expiryDate")
                ));
    }

    @Override
    public List<PassWithMemberDetails> findMemberPassById(int passId) {
        final String SQL = "SELECT id,p.memberId,serialNo,expiryDate,m.firstName as firstName,m.lastName as lastName FROM Pass as p INNER JOIN Member as m ON " +
                "p.memberId=m.memberId WHERE id=:passId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("passId", passId);

        List<PassWithMemberDetails> passList = jdbcTemplate.query(SQL, parameters, new PassWithMemberDetailsRowMapper());
        return passList;
    }

    @Override
    public List<PassWithMemberDetails> findMemberPassBySerialNo(Long serialNo) {
        final String SQL = "SELECT id,p.memberId,serialNo,expiryDate,m.firstName as firstName,m.lastName as lastName FROM Pass as p INNER JOIN Member as m ON " +
                "p.memberId=m.memberId WHERE serialNo=:serialNo";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("serialNo", serialNo);

        List<PassWithMemberDetails> passList = jdbcTemplate.query(SQL, new PassWithMemberDetailsRowMapper());
        return passList;
    }


    //not need this
//    @Override
//    public Date findExpiryBySerialNo(Long serialNo) {
//        final String SQL = "SELECT expiryDate FROM Pass WHERE serialNo = :serialNo";
//
//        SqlParameterSource serial = new MapSqlParameterSource()
//                .addValue("serialNo", serialNo);
//
//        Date expiry = jdbcTemplate.queryForObject(SQL,serial,Date.class);
//        return expiry;
//    }

    @Override
    public int addPass(Pass p) {

        KeyHolder holder = new GeneratedKeyHolder();

        final String SQL = "INSERT INTO Pass (memberId, serialNo, expiryDate) VALUES (:memberId, :SerialNo, :expiry)";

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p), holder);
        final int id = holder.getKey().intValue();
        return id;

    }

    @Override
    public int updatePass(int passId, Pass p) {
        p.setPassId(passId);
        final String SQL = "UPDATE Pass SET memberId=:memberId, serialNo=:serialNo, expiryDate=:expiry WHERE id=:passId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p));
    }

    @Override
    public boolean deletePass(int passId) {
        Pass p = new Pass();
        p.setPassId(passId);
        final String SQL = "DELETE FROM Pass WHERE id=:passId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(p)) > 0;
    }

}
