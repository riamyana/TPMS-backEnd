package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.EnrolledPackage;
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
        final String SQL = "INSERT INTO EnrolledPackage (passId, packageId, start, end, isActive) VALUES (:passId, :packageId, :start, :end, :isActive)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(e), holder);

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
                        rs.getInt("isActive")
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
                        rs.getInt("isActive")
                )
        );
    }
}
