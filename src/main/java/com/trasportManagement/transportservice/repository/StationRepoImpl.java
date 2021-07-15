package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository(value = "stationRepo")
public class StationRepoImpl implements StationRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addStation(Station s) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO Station (stationName,swipeMachineId,latitude,longitude) VALUES (:stationName,:swipeMachineId,:latitude,:longitude)";
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(s), holder);

        final int id = holder.getKey().intValue();
        return id;

    }

    @Override
    public int updateStation(int stationId, Station s) {
        s.setStationId(stationId);
        final String SQL = "UPDATE Station SET stationName=:stationName, swipeMachineId=:swipeMachineId, latitude=:latitude, longitude=:longitude WHERE stationId=:stationId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(s));
    }

    @Override
    public boolean deleteStation(int stationId) {
        Station s = new Station();
        s.setStationId(stationId);
        final String SQL = "DELETE FROM Station WHERE stationId=:stationId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(s)) > 0;
    }

    @Override
    public List<Station> findAllStation() {
        final String SQL = "SELECT * FROM Station";
        return jdbcTemplate.query(SQL, (rs, i) ->
                new Station(
                        rs.getInt("stationId"),
                        rs.getString("stationName"),
                        rs.getString("swipeMachineId"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                ));
    }

    @Override
    public List<Station> findStationById(int stationId) {
        final String SQL = "SELECT * FROM Station WHERE stationId=:stationId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("stationId", stationId);

        return jdbcTemplate.query(SQL, parameters, (rs, i) ->
                new Station(
                        rs.getInt("stationId"),
                        rs.getString("stationName"),
                        rs.getString("swipeMachineId"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                ));
    }
}
