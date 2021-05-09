package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.*;
import com.trasportManagement.transportservice.repository.StationRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    @Qualifier("stationRepo")
    StationRepo stationRepo;

    public Station addStation(Station s) {
        int n = stationRepo.addStation(s);
        if(n == 0){
            throw new TPMSCustomException("No record inserted for this station", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return s;
    }

    public Station updateStation(int stationId, Station s) {
        int n = stationRepo.updateStation(stationId,s);
        s.setStationId(stationId);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given station id : " + s.getStationId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return s;
    }

    public Boolean deleteStation(int stationId) {
        if(!stationRepo.deleteStation(stationId)){
            throw new TPMSCustomException("Unable to delete. Given station id : " + stationId   + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

    public List<Station> findAllStation() {
        List<Station> stations = stationRepo.findAllStation();
        if(stations.isEmpty()){
            throw  new TPMSCustomException("No station found.", HttpStatus.NOT_FOUND);
        }

        return stations;
    }

    //single data
    public List<Station> findStationById(int stationId) {
        List<Station> stationList = stationRepo.findStationById(stationId);
        if(stationList.isEmpty()){
            throw  new TPMSCustomException("No station found.", HttpStatus.NOT_FOUND);
        }

        return stationList;
    }
}
