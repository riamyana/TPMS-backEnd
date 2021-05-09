package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.TransportMode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportModeRepo {

    public int addTransportMode(TransportMode t);
    public int updateTransportMode(int id, TransportMode t);
    public boolean deleteTransportMode(int id);
    public List<TransportMode> findAllTransportMode();
}
