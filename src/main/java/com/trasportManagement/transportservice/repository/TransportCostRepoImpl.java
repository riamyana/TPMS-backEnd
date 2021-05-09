package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransCostFromToStation;
import com.trasportManagement.transportservice.model.TransCostWithStationDetails;
import com.trasportManagement.transportservice.model.TransportCost;
import com.trasportManagement.transportservice.repository.mapper.TransCostFromToStationMapper;
import com.trasportManagement.transportservice.repository.mapper.TransCostWithStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "transportCostRepo")
public class TransportCostRepoImpl implements TransportCostRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addTransportCost(TransportCost t) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO TransportCost (fromStationId, toStationId, cost) VALUES (:fromStationId, :toStationId, :cost)";
        int n =jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else {
            return 0;
        }
    }

    @Override
    public int updateTransportCost(int transCostId, TransportCost t) {
        t.setTransCostId(transCostId);
        final String sql = "UPDATE TransportCost SET fromStationId=:fromStationId, toStationId=:toStationId, cost=:cost WHERE transCostId=:transCostId";
        int n = jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t));
        return n;
    }

    @Override
    public int deleteTransportCost(int transCostId) {
        TransportCost t = new TransportCost();
        t.setTransCostId(transCostId);
        final String SQL = "DELETE FROM TransportCost WHERE transCostId=:transCostId";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(t));
    }

    @Override
    public List<TransCostWithStationDetails> findTransportCost() {
        String sql="SELECT sf.stationName as fromStationName, st.stationName as toStationName, cost\n" +
                "FROM TransportCost as t INNER JOIN Station as sf ON t.fromStationId=sf.stationId\n" +
                "INNER JOIN Station as st ON t.toStationId=st.stationId";
        List<TransCostWithStationDetails> transCostList = jdbcTemplate.query(sql, new TransCostWithStationMapper());
        return transCostList;
    }

    @Override
    public List<TransCostFromToStation> findCostFromToStation(int fromStationId, int toStationId) {
        String sql="SELECT cost FROM TransportCost WHERE fromStationId=" + fromStationId +" AND toStationId=" + toStationId;
        List<TransCostFromToStation> transCostList = jdbcTemplate.query(sql, new TransCostFromToStationMapper());
        return transCostList;
    }
}
