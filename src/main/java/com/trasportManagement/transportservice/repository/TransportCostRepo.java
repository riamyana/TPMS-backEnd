package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransCostFromToStation;
import com.trasportManagement.transportservice.model.TransCostWithStationDetails;
import com.trasportManagement.transportservice.model.TransportCost;

import java.util.List;

public interface TransportCostRepo {
    int addTransportCost(TransportCost t);

    int updateTransportCost(int transCostId, TransportCost t);

    boolean deleteTransportCost(int transCostId);

    List<TransCostWithStationDetails> findTransportCost();

    List<TransCostFromToStation> findCostFromToStation(int fromStationId, int toStationId);
}
