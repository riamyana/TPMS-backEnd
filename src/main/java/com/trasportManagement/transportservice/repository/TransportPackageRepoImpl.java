package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransportPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository(value="transportPackageRepo")
public class TransportPackageRepoImpl implements TransportPackageRepo {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addTransportPackage(TransportPackage tp) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO TransportPackage (transModetId, packageId) VALUES (:transModeId, :packageId)";
        int n = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(tp), holder);

        if (n > 0) {
            return holder.getKey().intValue();
        }

        return 0;
    }

    @Override
    public int updateTransportPackage(int id, TransportPackage tp) {
        tp.setId(id);
        final String SQL = "UPDATE TransportPackage SET transModetId=:transModeId, packageId=:packageId WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(tp));
    }

    @Override
    public int deleteTransportPackage(int id) {
        TransportPackage tp = new TransportPackage();
        tp.setId(id);
        final String SQL = "DELETE FROM TransportPackage WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(tp));
    }
}
