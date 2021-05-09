package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.PackageByTransportDTO;
import com.trasportManagement.transportservice.model.TransportPackage;
import com.trasportManagement.transportservice.model.TransportPackageDTO;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.TransportPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransportPackageController {

    @Autowired
    TransportPackageService transportPackageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/transport-modes-packages")
    public ResponseEntity<TransportPackage> addTransportPackage(@RequestBody(required=true) TransportPackage tp) {
        TransportPackage result = transportPackageService.addTransportPackage(tp);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/transport-modes-packages/{id}")
    public ResponseEntity<TransportPackage> updateTransportPackage(@PathVariable int id, @RequestBody(required=true) TransportPackage tp) {
        TransportPackage result = transportPackageService.updateTransportPackage(id,tp);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/transport-modes-packages/{id}")
    public ResponseEntity<Boolean> deleteTransportPackage(@PathVariable int id) {
        Boolean result = transportPackageService.deleteTransportPackage(id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/transport-modes-packages")
    public ResponseEntity<List<PackageByTransportDTO>> findAllTransportModePackages(){
        List<PackageByTransportDTO> passResult =transportPackageService.findAllTransportModePackages();
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }
}
