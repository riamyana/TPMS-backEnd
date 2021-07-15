package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.PackageByTransportDTO;
import com.trasportManagement.transportservice.model.TransportPackage;
import com.trasportManagement.transportservice.repository.resultSetExtractor.PackageByTransportExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="transportPackageRepo")
public class TransportPackageRepoImpl implements TransportPackageRepo {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addTransportPackage(TransportPackage tp) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO TransportPackage (transModetId, packageId) VALUES (:transModeId, :packageId)";
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(tp), holder);
        final int id = holder.getKey().intValue();
        return id;
    }

    @Override
    public int updateTransportPackage(int id, TransportPackage tp) {
        tp.setId(id);
        final String SQL = "UPDATE TransportPackage SET transModetId=:transModeId, packageId=:packageId WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(tp));
    }

    @Override
    public boolean deleteTransportPackage(int id) {
        TransportPackage tp = new TransportPackage();
        tp.setId(id);
        final String SQL = "DELETE FROM TransportPackage WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(tp)) > 0;
    }

    @Override
    public List<PackageByTransportDTO> findAllTransportModePackages() {
        final String SQL = "SELECT tp.id, p.id AS packageId, p.name AS packageName,p.validity as validity,p.balance AS " +
                            "balance,p.price AS price,t.id AS transportId, t.name as transportMode FROM Package AS p " +
                            "INNER JOIN TransportPackage AS tp ON p.id = tp.packageId INNER JOIN TransportMode AS t " +
                            "ON t.id = tp.transModetId";
        List<PackageByTransportDTO> result = (List<PackageByTransportDTO>) jdbcTemplate.query(SQL, new PackageByTransportExtractor());
        return result;
    }


}
