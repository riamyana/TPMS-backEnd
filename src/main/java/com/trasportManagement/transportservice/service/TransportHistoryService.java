package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.exception.TPMSCustomException;
import com.trasportManagement.transportservice.model.TransHistoryWithPassStationDetails;
import com.trasportManagement.transportservice.model.TransportHistory;
import com.trasportManagement.transportservice.repository.TransportHistoryRepo;
import com.trasportManagement.transportservice.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportHistoryService {
    @Autowired
    @Qualifier("transportHistoryRepo")
    TransportHistoryRepo transportHistoryRepo;

    public TransportHistory addTransportHistory(TransportHistory t) {
        int n = transportHistoryRepo.addTransportHistory(t);
        t.setTransHistoryId(n);
        if(n == 0){
            throw new TPMSCustomException("No record inserted of this Transport History", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return t;
    }

    public List<TransHistoryWithPassStationDetails> findTransportHistory() {
        List<TransHistoryWithPassStationDetails> transportHistoryList = transportHistoryRepo.findTransportHistory();
        if(transportHistoryList.isEmpty()){
            throw  new TPMSCustomException("No history found for Transportation.", HttpStatus.NOT_FOUND);
        }

        return transportHistoryList;
    }

    public List<TransHistoryWithPassStationDetails> findTransHistoryByMemberID(int memberId) {
        List<TransHistoryWithPassStationDetails> transportHistoryList = transportHistoryRepo.findTransHistoryByMemberID(memberId);
        if(transportHistoryList.isEmpty()){
            throw  new TPMSCustomException("No transport history found for member id : "+memberId, HttpStatus.NOT_FOUND);
        }

        return transportHistoryList;
    }
}
