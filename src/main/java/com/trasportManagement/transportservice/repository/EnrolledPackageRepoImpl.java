package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.EnrolledPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "enrolledPackageRepo")
public class EnrolledPackageRepoImpl implements EnrolledPackageRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addEnrolledPackage(EnrolledPackage e) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO EnrolledPackage (passId, packageId, transModeId, date) VALUES (:passId, :packageId, :transModeId, :date)";
        int n = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(e), holder);

        if(n > 0){
            return holder.getKey().intValue();
        }

        return 0;

    }

    @Override
    public int updateEnrolledPackage(int id, EnrolledPackage e) {
        e.setId(id);
        final String SQL = "UPDATE EnrolledPackage SET passId = :passId, packageId = :packageId, transModeId = :transModeId, date = :date WHERE id=:id";
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
        final String SQL = "SELECT * FROM EnrolledPackage";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new EnrolledPackage(
                        rs.getInt("id"),
                        rs.getInt("passId"),
                        rs.getInt("packageId"),
                        rs.getInt("transModeId"),
                        rs.getDate("date")
                )
        );
    }
}
