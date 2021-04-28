package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.TransHistoryWithPassStationDetails;
import com.trasportManagement.transportservice.model.TransportHistory;
import com.trasportManagement.transportservice.repository.TransportHistoryRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportHistoryService {
    @Autowired
    @Qualifier("transportHistoryRepo")
    TransportHistoryRepo transportHistoryRepo;

    public Result<TransportHistory> addTransportHistory(TransportHistory t) {
        int transHistoryId = transportHistoryRepo.addTransportHistory(t);
        if(transHistoryId == 0){
            return new Result<>(400, "Error in adding history of transportation.");
        }else{
            t.setTransHistoryId(transHistoryId);
            // Code : 201 for Insert (POST)
            return new Result<>(201, t);
        }
    }

    public Result<List<TransHistoryWithPassStationDetails>> findTransportHistory() {
        List<TransHistoryWithPassStationDetails> transportHistoryList = transportHistoryRepo.findTransportHistory();
        if(transportHistoryList.size() > 0){
            return new Result<>(200, transportHistoryList);
        }
        else{
            return new Result<>(400, "No history found for Transportation .");
        }
    }

    public Result<List<TransHistoryWithPassStationDetails>> findTransHistoryByMemberID(int memberId) {
        List<TransHistoryWithPassStationDetails> transportHistoryList = transportHistoryRepo.findTransHistoryByMemberID(memberId);
        if(transportHistoryList.size() > 0){
            return new Result<>(200, transportHistoryList);
        }
        else{
            return new Result<>(400, "No transport history found for member id : "+memberId);
        }
    }
}
