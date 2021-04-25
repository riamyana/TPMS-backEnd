package com.trasportManagement.transportservice.repository.mapper;

import com.trasportManagement.transportservice.model.TransportMode;
import com.trasportManagement.transportservice.model.TransportPackageDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportPackageExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, TransportPackageDTO> packageMap = new HashMap<>();

        while(rs.next()){
            int id = rs.getInt("packageId");
            TransportPackageDTO transportPackageDTO = packageMap.get(id);
            if(transportPackageDTO == null){
                transportPackageDTO = new TransportPackageDTO();
                transportPackageDTO.setId(rs.getInt("packageId"));
                transportPackageDTO.setName(rs.getString("packageName"));
                transportPackageDTO.setValidity(rs.getInt("validity"));
                transportPackageDTO.setBalance(rs.getInt("balance"));
                packageMap.put(id, transportPackageDTO);
            }

            List modeList = transportPackageDTO.getTransportModes();

            if(modeList == null){
                modeList = new ArrayList<TransportMode>();
                transportPackageDTO.setTransportModes(modeList);
            }

            TransportMode transportMode = new TransportMode();
            transportMode.setId(rs.getInt("transportId"));
            transportMode.setName(rs.getString("transportMode"));
            modeList.add(transportMode);
        }

        System.out.println(packageMap.values());
        return new ArrayList<TransportPackageDTO>(packageMap.values());
    }
}
