package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.TransHistoryWithPassStationDetails;
import com.trasportManagement.transportservice.model.TransportHistory;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.TransportHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TransportHistoryController {
    @Autowired
    TransportHistoryService transportHistoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/transports-history")
    public ResponseEntity<TransportHistory> addTransportHistory(@RequestBody(required=true) TransportHistory t) {
        TransportHistory transportHistoryResult = transportHistoryService.addTransportHistory(t);
        return new ResponseEntity<>(transportHistoryResult, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/transports-history")
    public ResponseEntity<List<TransHistoryWithPassStationDetails>> getTransportHistory() {
        List<TransHistoryWithPassStationDetails> transportHistoryResult = transportHistoryService.findTransportHistory();
        return new ResponseEntity<>(transportHistoryResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/transports-history/members/{memberId}")
    public ResponseEntity<List<TransHistoryWithPassStationDetails>> getTransHistoryByMemberID(@PathVariable int memberId) {
        List<TransHistoryWithPassStationDetails> transportHistoryResult = transportHistoryService.findTransHistoryByMemberID(memberId);
        return new ResponseEntity<>(transportHistoryResult, HttpStatus.OK);
    }
}
