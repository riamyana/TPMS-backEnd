package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransHistoryWithPassStationDetails;
import com.trasportManagement.transportservice.model.TransportHistory;
import com.trasportManagement.transportservice.repository.mapper.TransportHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository(value = "transportHistoryRepo")
public class TransportHistoryImpl implements TransportHistoryRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addTransportHistory(TransportHistory t) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String SQL = "INSERT INTO TransportHistory (passId,fromStationId, toStationId,fromDateTime,toDateTime) VALUES (:passId,:fromStationId,:toStationId,:fromDateTime,:toDateTime)";
        return jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(t), holder);

    }

    @Override
    public List<TransHistoryWithPassStationDetails> findTransportHistory() {
        final String SQL = "SELECT transHistoryId,t.passId as passID,serialNo,sf.stationName as fromStationName, st.stationName as toStationName, fromDateTime, toDateTime " +
                "FROM TransportHistory as t INNER JOIN Station as sf ON t.fromStationId=sf.stationId " +
                "INNER JOIN Station as st ON t.toStationId=st.stationId INNER JOIN Pass as p ON t.passId=p.id";
        List<TransHistoryWithPassStationDetails> transHistoryList = jdbcTemplate.query(SQL, new TransportHistoryMapper());
        return transHistoryList;
    }

    @Override
    public List<TransHistoryWithPassStationDetails> findTransHistoryByMemberID(int memberId) {
        final String SQL= "SELECT transHistoryId,t.passId as passID,serialNo,sf.stationName as fromStationName, st.stationName as toStationName, fromDateTime, toDateTime " +
                "FROM TransportHistory as t INNER JOIN Station as sf ON t.fromStationId=sf.stationId " +
                "INNER JOIN Station as st ON t.toStationId=st.stationId INNER JOIN Pass as p ON t.passId=p.id AND memberID=:memberId";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("memberId", memberId);

        List<TransHistoryWithPassStationDetails> transHistoryList = jdbcTemplate.query(SQL, parameters, new TransportHistoryMapper());
        return transHistoryList;
    }
}
