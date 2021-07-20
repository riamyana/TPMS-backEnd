package com.trasportManagement.transportservice.repository;

import com.trasportManagement.transportservice.model.Graph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraphRepo {
    List<Graph> lastYearPackages();

    List<Graph> lastYearTransportPackages();
}
