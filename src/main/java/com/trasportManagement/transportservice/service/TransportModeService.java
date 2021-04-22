package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.TransportMode;
import com.trasportManagement.transportservice.repository.TransportModeRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportModeService {

    @Autowired
    @Qualifier("transportModeRepo")
    TransportModeRepo transportModeRepo;

    public Result<TransportMode> addTransportMode(TransportMode t) {

        int result = transportModeRepo.addTransportMode(t);

        if(result == 0){
            return new Result<>(400, "Error in adding transport mode details.");
        }else if(result < 0){
            return new Result<>(400, "Transport mode name already exists.");
        }

        t.setId(result);
        return new Result<>(201, t);
    }

    public Result<TransportMode> updateTransportMode(int id, TransportMode t) {

        int rows = transportModeRepo.updateTransportMode(id,t);

        if(rows > 0){
            return new Result<>(201, t);
        }

        return new Result<>(400, "Unable to update. Given Transport Mode id : " + t.getId()   + " not found.");
    }

    public Result<TransportMode> deleteTransportMode(int id) {
        int rows = transportModeRepo.deleteTransportMode(id);

        if(rows > 0){
            return new Result<>(200, "Transport mode with id : " + id + " deleted successfully.");
        }

        return new Result<>(400, "Unable to delete. Given transport mode id : " + id   + " not found.");
    }

    public Result<List<TransportMode>> findAllTransportMode() {

        List<TransportMode> modes = transportModeRepo.findAllTransportMode();

        if (modes.size() > 0) {
            return new Result<>(200, modes);
        }

        return new Result<>(400, "No transport mode found.");
    }
}
