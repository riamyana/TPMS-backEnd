package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.TransportMode;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.TransportModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransportModeController {

    @Autowired
    TransportModeService transportModeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/transport-modes")
    public ResponseEntity<Result<TransportMode>> addTransportMode(@RequestBody(required=true) TransportMode t) {

        Result<TransportMode>  modeResult= transportModeService.addTransportMode(t);

        return new ResponseEntity<>(modeResult, HttpStatus.valueOf(modeResult.getCode()));
    }

    @GetMapping("/transport-modes")
    public ResponseEntity<Result<List<TransportMode>>> findAllTransportMode() {

        Result<List<TransportMode>>  modeResult= transportModeService.findAllTransportMode();
        return new ResponseEntity<>(modeResult, HttpStatus.valueOf(modeResult.getCode()));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/transport-modes/{id}")
    public ResponseEntity<Result<TransportMode>> updateTransportMode(@PathVariable int id, @RequestBody(required=true) TransportMode t) {

        Result<TransportMode>  modeResult= transportModeService.updateTransportMode(id,t);
        return new ResponseEntity<>(modeResult, HttpStatus.valueOf(modeResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/transport-modes/{id}")
    public ResponseEntity<Result<TransportMode>> deleteTransportMode(@PathVariable int id) {

        Result<TransportMode>  modeResult= transportModeService.deleteTransportMode(id);
        return new ResponseEntity<>(modeResult, HttpStatus.valueOf(modeResult.getCode()));

    }
}
