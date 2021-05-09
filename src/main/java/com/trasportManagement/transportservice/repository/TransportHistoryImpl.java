package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransHistoryWithPassStationDetails;
import com.trasportManagement.transportservice.model.TransportHistory;
import com.trasportManagement.transportservice.repository.mapper.TransportHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Repository(value = "transportHistoryRepo")
public class TransportHistoryImpl implements TransportHistoryRepo{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int addTransportHistory(TransportHistory t) {

//        Date date=new Date();
//        Timestamp startDate = new Timestamp(date.getTime());
//        System.out.println(startDate);
//        t.setFromDateTime(startDate);

        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO TransportHistory (passId,fromStationId, toStationId,fromDateTime,toDateTime) VALUES (:passId,:fromStationId,:toStationId,:fromDateTime,:toDateTime)";
        int n =jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), holder);
        if(n > 0){
            return holder.getKey().intValue();
        }
        else {
            return 0;
        }
    }

    @Override
    public List<TransHistoryWithPassStationDetails> findTransportHistory() {
        String sql="SELECT transHistoryId,t.passId as passID,serialNo,sf.stationName as fromStationName, st.stationName as toStationName, fromDateTime, toDateTime\n" +
                "FROM TransportHistory as t INNER JOIN Station as sf ON t.fromStationId=sf.stationId\n" +
                "INNER JOIN Station as st ON t.toStationId=st.stationId INNER JOIN Pass as p ON t.passId=p.id";
        List<TransHistoryWithPassStationDetails> transHistoryList = jdbcTemplate.query(sql, new TransportHistoryMapper());
        return transHistoryList;
    }

    @Override
    public List<TransHistoryWithPassStationDetails> findTransHistoryByMemberID(int memberId) {
        String sql="SELECT transHistoryId,t.passId as passID,serialNo,sf.stationName as fromStationName, st.stationName as toStationName, fromDateTime, toDateTime\n" +
                "FROM TransportHistory as t INNER JOIN Station as sf ON t.fromStationId=sf.stationId\n" +
                "INNER JOIN Station as st ON t.toStationId=st.stationId INNER JOIN Pass as p ON t.passId=p.id AND memberID="+memberId;
        List<TransHistoryWithPassStationDetails> transHistoryList = jdbcTemplate.query(sql, new TransportHistoryMapper());
        return transHistoryList;
    }
}
