package com.trasportManagement.transportservice.repository;


import com.trasportManagement.transportservice.model.TransHistoryWithPassStationDetails;
import com.trasportManagement.transportservice.model.TransportHistory;

import java.util.List;

public interface TransportHistoryRepo {
    int addTransportHistory(TransportHistory t);


    List<TransHistoryWithPassStationDetails> findTransportHistory();

    List<TransHistoryWithPassStationDetails> findTransHistoryByMemberID(int memberId);
}
