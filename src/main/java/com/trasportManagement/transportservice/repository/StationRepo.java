package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.model.Station;

import java.util.List;

public interface StationRepo {
    public int addStation(Station s);
    public int updateStation(int stationId, Station s);
    public int deleteStation(int stationId);
    List<Station> findAllStation();
    List<Station> findStationById(int stationId);

}
