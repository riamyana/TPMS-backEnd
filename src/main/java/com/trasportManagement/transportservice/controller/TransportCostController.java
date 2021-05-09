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
    public ResponseEntity<Result<TransportCost>> addTransportCost(@RequestBody(required=true) TransportCost t) {
        Result<TransportCost> transportCostResult = transportCostService.addTransportCost(t);
        return new ResponseEntity<>(transportCostResult, HttpStatus.valueOf(transportCostResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/transports-costs/{transCostId}")
    public ResponseEntity<Result<TransportCost>> updateTransportCost(@PathVariable int transCostId, @RequestBody(required=true) TransportCost t) {
        Result<TransportCost> transportCostResult = transportCostService.updateTransportCost(transCostId,t);
        return new ResponseEntity<>(transportCostResult, HttpStatus.valueOf(transportCostResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/transports-cost/{transCostId}")
    public ResponseEntity<Result<TransportCost>> deleteTransportCost(@PathVariable int transCostId) {
        Result<TransportCost> transportCostResult = transportCostService.deleteTransportCost(transCostId);
        return new ResponseEntity<>(transportCostResult, HttpStatus.valueOf(transportCostResult.getCode()));
    }


    @GetMapping("/transports-cost")
    public ResponseEntity<Result<List<TransCostWithStationDetails>>> getTransportCost() {
        Result<List<TransCostWithStationDetails>> result = transportCostService.findTransportCost();
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }


    @GetMapping("/transports-cost/from-station/{fromStationId}/to-station/{toStationId}")
    public ResponseEntity<Result<List<TransCostFromToStation>>> getCostFromToStation(@PathVariable int fromStationId, @PathVariable int toStationId) {
        Result <List<TransCostFromToStation>> result = transportCostService.findCostFromToStation(fromStationId,toStationId);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
}
