package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.TransportPackage;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.TransportPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransportPackageController {

    @Autowired
    TransportPackageService transportPackageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/transport-modes-packages")
    public ResponseEntity<Result<TransportPackage>> addTransportPackage(@RequestBody(required=true) TransportPackage tp) {
        Result<TransportPackage> result = transportPackageService.addTransportPackage(tp);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/transport-modes-packages/{id}")
    public ResponseEntity<Result<TransportPackage>> updateTransportPackage(@PathVariable int id, @RequestBody(required=true) TransportPackage tp) {
        Result<TransportPackage> result = transportPackageService.updateTransportPackage(id,tp);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/transport-modes-packages/{id}")
    public ResponseEntity<Result<TransportPackage>> deleteTransportPackage(@PathVariable int id) {
        Result<TransportPackage> result = transportPackageService.deleteTransportPackage(id);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
}
