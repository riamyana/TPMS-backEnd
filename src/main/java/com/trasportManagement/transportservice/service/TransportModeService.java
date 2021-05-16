package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.TransportMode;
import com.trasportManagement.transportservice.repository.TransportModeRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportModeService {

    @Autowired
    @Qualifier("transportModeRepo")
    TransportModeRepo transportModeRepo;

    public TransportMode addTransportMode(TransportMode t) {

        int n = transportModeRepo.addTransportMode(t);

        if(n == 0){
            throw new TPMSCustomException("No record inserted of Transport Mode", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return t;
    }

    public TransportMode updateTransportMode(int id, TransportMode t) {

        int n = transportModeRepo.updateTransportMode(id,t);
        t.setId(id);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given Transport Mode id : "+ t.getId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return t;

    }

    public Boolean deleteTransportMode(int id) {

        if(!transportModeRepo.deleteTransportMode(id)){
            throw new TPMSCustomException("Unable to delete. Given transport mode id : " + id   + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

    public List<TransportMode> findAllTransportMode() {

        List<TransportMode> modes = transportModeRepo.findAllTransportMode();

        if(modes.isEmpty()){
            throw  new TPMSCustomException("No transport mode found.", HttpStatus.NOT_FOUND);
        }

        return modes;
    }
}
