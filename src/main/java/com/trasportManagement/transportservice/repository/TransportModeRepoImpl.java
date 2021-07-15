package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.TransportMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "transportModeRepo")
public class TransportModeRepoImpl implements TransportModeRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<TransportMode> findTransportModeByName(String name){
        TransportMode t = new TransportMode();

        final String SELECT_MODE = "SELECT * FROM TransportMode WHERE name=:name";

        SqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("name", name);

        return jdbcTemplate.query(SELECT_MODE, parameter, (rs, i) ->
        new TransportMode(
                rs.getInt("id"),
                rs.getString("name")
        ));
    }

    @Override
    public int addTransportMode(TransportMode t) {

        KeyHolder holder = new GeneratedKeyHolder();

        final String SQL = "INSERT INTO TransportMode (id, name) VALUES (:id, :name)";

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(t), holder);

        final int id = holder.getKey().intValue();
        return id;

    }

    @Override
    public int updateTransportMode(int id, TransportMode t) {
        t.setId(id);
        final String SQL = "UPDATE TransportMode SET name=:name WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(t));
    }

    @Override
    public boolean deleteTransportMode(int id) {
        TransportMode t = new TransportMode();
        t.setId(id);
        final String SQL = "DELETE FROM TransportMode WHERE id=:id";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(t)) > 0;
    }

    @Override
    public List<TransportMode> findAllTransportMode(){
        final String SQL = "SELECT * FROM TransportMode";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new TransportMode(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }
}
