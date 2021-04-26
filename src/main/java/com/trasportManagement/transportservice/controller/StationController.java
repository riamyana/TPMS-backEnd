package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.model.Station;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StationController {
    @Autowired
    StationService stationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/stations")
    public ResponseEntity<Result<Station>> addStation(@RequestBody(required=true) Station s) {
        Result<Station> stationResult = stationService.addStation(s);
        return new ResponseEntity<>(stationResult, HttpStatus.valueOf(stationResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/stations/{stationId}")
    public ResponseEntity<Result<Station>> updateStation(@PathVariable int stationId, @RequestBody(required=true) Station s) {
        Result<Station> stationResult = stationService.updateStation(stationId,s);
        return new ResponseEntity<>(stationResult, HttpStatus.valueOf(stationResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/stations/{stationId}")
    public ResponseEntity<Result<Station>> deleteStation(@PathVariable int stationId) {
        Result<Station> stationResult = stationService.deleteStation(stationId);
        return new ResponseEntity<>(stationResult, HttpStatus.valueOf(stationResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/stations")
    public ResponseEntity<Result<List<Station>>> getStations(){
        Result<List<Station>> stationResult =stationService.findAllStation();
        return new ResponseEntity<>(stationResult, HttpStatus.valueOf(stationResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/stations/{stationId}")
    public ResponseEntity<Result<Station>> getStationById(@PathVariable int stationId){
        Result<Station> stationResult =stationService.findStationById(stationId);
        return new ResponseEntity<>(stationResult, HttpStatus.valueOf(stationResult.getCode()));
    }
}
