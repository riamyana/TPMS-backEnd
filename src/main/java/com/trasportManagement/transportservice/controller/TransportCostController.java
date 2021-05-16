package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.*;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.TransportCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransportCostController {

    @Autowired
    TransportCostService transportCostService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/transports-cost")
    public ResponseEntity<TransportCost> addTransportCost(@RequestBody(required=true) TransportCost t) {
        TransportCost transportCostList = transportCostService.addTransportCost(t);
        return new ResponseEntity<>(transportCostList, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/transports-costs/{transCostId}")
    public ResponseEntity<TransportCost> updateTransportCost(@PathVariable int transCostId, @RequestBody(required=true) TransportCost t) {
        TransportCost transportCostList = transportCostService.updateTransportCost(transCostId,t);
        return new ResponseEntity<>(transportCostList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/transports-cost/{transCostId}")
    public ResponseEntity<Boolean> deleteTransportCost(@PathVariable int transCostId) {
        Boolean transportCostList = transportCostService.deleteTransportCost(transCostId);
        return new ResponseEntity<>(transportCostList, HttpStatus.NO_CONTENT);
    }


    @GetMapping("/transports-cost")
    public ResponseEntity<List<TransCostWithStationDetails>> getTransportCost() {
        List<TransCostWithStationDetails> transportCostList = transportCostService.findTransportCost();
        return new ResponseEntity<>(transportCostList, HttpStatus.OK);
    }


    @GetMapping("/transports-cost/from-station/{fromStationId}/to-station/{toStationId}")
    public ResponseEntity<List<TransCostFromToStation>> getCostFromToStation(@PathVariable int fromStationId, @PathVariable int toStationId) {
        List<TransCostFromToStation> transportCostList = transportCostService.findCostFromToStation(fromStationId,toStationId);
        return new ResponseEntity<>(transportCostList, HttpStatus.OK);
    }
}
