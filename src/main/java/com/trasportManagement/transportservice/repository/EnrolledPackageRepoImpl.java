package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.EnrolledMemberPackage;
import com.trasportManagement.transportservice.model.EnrolledPackage;
import com.trasportManagement.transportservice.model.PackageForMember;
import com.trasportManagement.transportservice.repository.mapper.EnrolledMemberPackageRowMapper;
import com.trasportManagement.transportservice.repository.mapper.PackageForMemberRowMapper;
import com.trasportManagement.transportservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository(value = "enrolledPackageRepo")
public class EnrolledPackageRepoImpl implements EnrolledPackageRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private PackageService packageService;

    @Override
    public int addEnrolledPackage(EnrolledPackage e) {

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        System.out.println("date"+df.format(startDate));

        int validity = packageService.findValidityById(e.getPackageId());

        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE,validity);

        Date endDate = c.getTime();

        e.setStart(startDate);
        e.setEnd(endDate);

        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO EnrolledPackage (passId, packageId, start, end, isActive, amount) VALUES (:passId, :packageId, :start, :end, :isActive, :amount)";
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(e), holder);

        final int id = holder.getKey().intValue();
        return id;

    }

    @Override
    public int updateIsActive(EnrolledPackage e) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "Update EnrolledPackage SET isActive=:isActive WHERE passId=:passId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("isActive", e.getIsActive())
                .addValue("passId", e.getPassId());

        int n = jdbcTemplate.update(SQL, parameters, holder);

        return n;
    }

    @Override
    public List<EnrolledPackage> findAllEnrolledPackage() {
        final String SQL = "SELECT * FROM EnrolledPackage where isActive=1";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new EnrolledPackage(
                        rs.getInt("id"),
                        rs.getInt("passId"),
                        rs.getInt("packageId"),
                        rs.getDate("start"),
                        rs.getDate("end"),
                        rs.getInt("isActive"),
                        rs.getDouble("amount")
                )
        );
    }

    @Override
    public List<EnrolledPackage> findEnrolledPackageByPassId(int passId) {
        final String SQL = "SELECT * FROM EnrolledPackage where isActive=1 and passId=:passId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("passId", passId);

        return jdbcTemplate.query(SQL, parameters, (rs, i) ->
                new EnrolledPackage(
                        rs.getInt("id"),
                        rs.getInt("passId"),
                        rs.getInt("packageId"),
                        rs.getDate("start"),
                        rs.getDate("end"),
                        rs.getInt("isActive"),
                        rs.getDouble("amount")
                )
        );
    }

    @Override
    public List<EnrolledMemberPackage> findEnrolledPkgByPassId(int passId) {
        final String SQL = "SELECT ep.* , p.name, p.transportMode,p.subscriptionType FROM Package AS p, EnrolledPackage as ep WHERE ep.packageId=p.id and ep.isActive=1 and ep.passId=:passId";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("passId", passId);
        List<EnrolledMemberPackage> result = jdbcTemplate.query(SQL, parameters, new EnrolledMemberPackageRowMapper());
        return result;
    }

}
