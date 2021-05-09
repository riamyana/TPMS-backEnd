package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Station;

import java.util.List;

public interface StationRepo {
    public int addStation(Station s);
    public int updateStation(int stationId, Station s);
    public boolean deleteStation(int stationId);
    List<Station> findAllStation();
    List<Station> findStationById(int stationId);

}
