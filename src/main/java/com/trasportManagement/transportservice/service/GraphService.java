package com.trasportManagement.transportservice.service;

import com.trasportManagement.transportservice.model.Graph;
import com.trasportManagement.transportservice.repository.GraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphService {

    @Qualifier("graphRepoImpl")
    @Autowired
    GraphRepo graphRepo;

    public List<Graph> lastYearPackages() {
        List<Graph> graphList = graphRepo.lastYearPackages();

        return graphList;
    }

    public List<Graph> lastYearTransportPackages() {
        List<Graph> graphList = graphRepo.lastYearTransportPackages();

        return graphList;
    }
}
