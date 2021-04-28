package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.EnrolledPackage;
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

    @Override
    public int addEnrolledPackage(EnrolledPackage e) {

        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        System.out.println("date"+df.format(startDate));

        final String SELECT_VALIDITY = "SELECT validity FROM Package WHERE id=:packageId";

        SqlParameterSource packageId = new MapSqlParameterSource()
                .addValue("packageId", e.getPackageId());

        int validity = jdbcTemplate.queryForObject(SELECT_VALIDITY, packageId, Integer.class);

        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE,validity);

        Date endDate = c.getTime();

//        System.out.println("start"+startDate);
//        System.out.println("validity"+validity);
//        System.out.println("end"+endDate);

        e.setStart(startDate);
        e.setEnd(endDate);

        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO EnrolledPackage (passId, packageId, start, end, isActive) VALUES (:passId, :packageId, :start, :end, :isActive)";
        int n = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(e), holder);

        if(n > 0){
            return holder.getKey().intValue();
        }

        return 0;

    }

    @Override
    public int updateEnrolledPackage(int id, EnrolledPackage e) {
        e.setId(id);
        final String SQL = "UPDATE EnrolledPackage SET passId = :passId, packageId = :packageId, start=:start, end=:end, isActive=:isActive WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(e));
    }

    @Override
    public int deleteEnrolledPackage(int id) {
        EnrolledPackage e = new EnrolledPackage();
        e.setId(id);
        final String SQL = "DELETE FROM EnrolledPackage WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(e));
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
}
