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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StationController {
    @Autowired
    StationService stationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/stations")
    public ResponseEntity<Station> addStation(@RequestBody(required=true) Station s) {
        Station stationList = stationService.addStation(s);
        return new ResponseEntity<>(stationList, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/stations/{stationId}")
    public ResponseEntity<Station> updateStation(@PathVariable int stationId, @RequestBody(required=true) Station s) {
        Station stationList = stationService.updateStation(stationId,s);
        return new ResponseEntity<>(stationList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/stations/{stationId}")
    public ResponseEntity<Boolean> deleteStation(@PathVariable int stationId) {
        Boolean stationList = stationService.deleteStation(stationId);
        return new ResponseEntity<>(stationList, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getStations(){
        List<Station> stationList =stationService.findAllStation();
        return new ResponseEntity<>(stationList, HttpStatus.OK);
    }


    @GetMapping("/stations/{stationId}")
    public ResponseEntity<List<Station>> getStationById(@PathVariable int stationId){
        List<Station> stationList =stationService.findStationById(stationId);
        return new ResponseEntity<>(stationList, HttpStatus.OK);
    }
}
