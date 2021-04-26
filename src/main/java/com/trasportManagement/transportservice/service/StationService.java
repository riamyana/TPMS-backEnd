package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.*;
import com.trasportManagement.transportservice.repository.StationRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    @Qualifier("stationRepo")
    StationRepo stationRepo;

    public Result<Station> addStation(Station s) {
        int stationId = stationRepo.addStation(s);
        if(stationId == 0){
            return new Result<>(400, "Error in adding terminal station details.");
        }else{
            s.setStationId(stationId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, s);
        }
    }

    public Result<Station> updateStation(int stationId, Station s) {
        int rows = stationRepo.updateStation(stationId,s);
        if(rows > 0){
            return new Result<>(200, s);
        }
        else{
            return new Result<>(400, "Unable to update. Given station id : " + s.getStationId()   + " not found.");
        }
    }

    public Result<Station> deleteStation(int stationId) {
        int rows = stationRepo.deleteStation(stationId);
        if(rows > 0){
            return new Result<>(200, "Station with id : " + stationId + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given station id : " + stationId   + " not found.");
        }
    }

    public Result<List<Station>> findAllStation() {
        List<Station> stations = stationRepo.findAllStation();
        if(stations.size() > 0){
            return new Result<>(200, stations);
        }
        else{
            return new Result<>(400, "No station found.");
        }
    }

    public Result<Station> findStationById(int stationId) {
        List<Station> stationList = stationRepo.findStationById(stationId);
        if(stationList.size() > 0){
            return new Result<>(200, stationList.get(0));
        }
        else{
            return new Result<>(400, "No station found.");
        }
    }
}
