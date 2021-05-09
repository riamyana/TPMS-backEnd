package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.TransCostFromToStation;
import com.trasportManagement.transportservice.model.TransCostWithStationDetails;
import com.trasportManagement.transportservice.model.TransportCost;
import com.trasportManagement.transportservice.repository.TransCostFromToStationMapper;
import com.trasportManagement.transportservice.repository.TransportCostRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportCostService {

    @Autowired
    @Qualifier("transportCostRepo")
    TransportCostRepo transportCostRepo;

    public TransportCost addTransportCost(TransportCost t) {
        int n = transportCostRepo.addTransportCost(t);
        if(n == 0){
            throw new TPMSCustomException("No record inserted for this Transport Cost", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return t;
    }

    public TransportCost updateTransportCost(int transCostId, TransportCost t) {
        int n = transportCostRepo.updateTransportCost(transCostId,t);
        t.setTransCostId(transCostId);

        if(n == 0){
            throw new TPMSCustomException("Unable to update. Given Transport cost id : " + t.getTransCostId()   + " not found.", HttpStatus.NOT_FOUND);
        }

        return t;
    }

    public Boolean deleteTransportCost(int transCostId) {
        if(!transportCostRepo.deleteTransportCost(transCostId)){
            throw new TPMSCustomException("Unable to delete. Given Transport cost id : " + transCostId   + " not found.", HttpStatus.BAD_REQUEST);
        }

        return true;
    }

    public List<TransCostWithStationDetails> findTransportCost() {
        List<TransCostWithStationDetails> transCostList= transportCostRepo.findTransportCost();
        if(transCostList.isEmpty()){
            throw  new TPMSCustomException("No details found for Transport cost.", HttpStatus.NOT_FOUND);
        }

        return transCostList;
    }

    public List<TransCostFromToStation> findCostFromToStation(int fromStationId, int toStationId) {
        List<TransCostFromToStation> transCostList=transportCostRepo.findCostFromToStation(fromStationId,toStationId);
        if(transCostList.isEmpty()){
            throw  new TPMSCustomException("No cost found for given stations", HttpStatus.NOT_FOUND);
        }

        return transCostList;
    }
}
