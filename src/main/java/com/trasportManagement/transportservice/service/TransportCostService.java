package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.TransCostFromToStation;
import com.trasportManagement.transportservice.model.TransCostWithStationDetails;
import com.trasportManagement.transportservice.model.TransportCost;
import com.trasportManagement.transportservice.repository.TransportCostRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportCostService {

    @Autowired
    @Qualifier("transportCostRepo")
    TransportCostRepo transportCostRepo;

    public Result<TransportCost> addTransportCost(TransportCost t) {
        int transCostId = transportCostRepo.addTransportCost(t);
        if(transCostId == 0){
            return new Result<>(400, "Error in adding cost of transport.");
        }else{
            t.setTransCostId(transCostId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, t);
        }
    }

    public Result<TransportCost> updateTransportCost(int transCostId, TransportCost t) {
        int rows = transportCostRepo.updateTransportCost(transCostId,t);
        if(rows > 0){
            return new Result<>(200, t);
        }
        else{
            return new Result<>(400, "Unable to update. Given Transport cost id : " + t.getTransCostId()   + " not found.");
        }
    }

    public Result<TransportCost> deleteTransportCost(int transCostId) {
        int rows = transportCostRepo.deleteTransportCost(transCostId);
        if(rows > 0){
            return new Result<>(200, "Transport cost with id : " + transCostId + " deleted successfully.");
        }
        else{
            return new Result<>(400, "Unable to delete. Given Transport cost id : " + transCostId   + " not found.");
        }
    }

    public Result<List<TransCostWithStationDetails>> findTransportCost() {
        List<TransCostWithStationDetails> transCostList= transportCostRepo.findTransportCost();
        if(transCostList.size() > 0){
            return new Result<>(200, transCostList);
        }
        else{
            return new Result<>(400, "No details found for Transport cost.");
        }
    }

    public Result<List<TransCostFromToStation>> findCostFromToStation(int fromStationId, int toStationId) {
        List<TransCostFromToStation> transCostList=transportCostRepo.findCostFromToStation(fromStationId,toStationId);
        if(transCostList.size()>0){
            return new Result<>(200, transCostList);
        }
        else{
            return new Result<>(400, "No cost found for given stations");
        }
    }
}
