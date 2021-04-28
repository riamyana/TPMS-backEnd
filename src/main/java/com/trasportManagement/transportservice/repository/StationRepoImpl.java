package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
        String sql = "INSERT INTO Station (stationName,swipeMachineId,latitude,longitude) VALUES (:stationName,:swipeMachineId,:latitude,:longitude)";
        int n =jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(s), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else {
            return 0;
        }
    }

    @Override
    public int updateStation(int stationId, Station s) {
        s.setStationId(stationId);
        final String SQL = "UPDATE Station SET stationName=:stationName, swipeMachineId=:swipeMachineId, latitude=:latitude, longitude=:longitude WHERE stationId=:stationId";
        int n = jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(s));
        return n;
    }

    @Override
    public int deleteStation(int stationId) {
        Station s = new Station();
        s.setStationId(stationId);
        final String SQL = "DELETE FROM Station WHERE stationId=:stationId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(s));
    }

    @Override
    public List<Station> findAllStation() {
        final String sql = "SELECT * FROM Station";
        return jdbcTemplate.query(sql, (rs, i) ->
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
        String sql = "SELECT * FROM Station WHERE stationId = " +stationId;
        return jdbcTemplate.query(sql, (rs, i) ->
                new Station(
                        rs.getInt("stationId"),
                        rs.getString("stationName"),
                        rs.getString("swipeMachineId"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                ));
    }
}
