package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    PackageService packageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/packages")
    public ResponseEntity<Result<Package>> addPass(@RequestBody(required=true) Package p) {
        Result<Package> passResult = packageService.addPackage(p);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @GetMapping("/packages")
    public ResponseEntity<Result<List<Package>>> getMemberPassById(){
        Result<List<Package>> passResult = packageService.findAllPackage();
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/packages/{id}")
    public ResponseEntity<Result<Package>> updatePass(@PathVariable int id,@RequestBody(required=true) Package p) {
        Result<Package> passResult = packageService.updatePackage(id,p);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/packages/{id}")
    public ResponseEntity<Result<Package>> deletePass(@PathVariable int id) {
        Result<Package> passResult = packageService.deletePackage(id);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

}
