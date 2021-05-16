package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.Package;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.model.SubscriptionType;
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
    public ResponseEntity<Package> addPackage(@RequestBody(required=true) Package p) {
        Package result = packageService.addPackage(p);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/packages/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable int id,@RequestBody(required=true) Package p) {
        Package passResult = packageService.updatePackage(id,p);
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/packages/{id}")
    public ResponseEntity<Boolean> deletePackage(@PathVariable int id) {
        Boolean passResult = packageService.deletePackage(id);
        return new ResponseEntity<>(passResult, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/packages")
    public ResponseEntity<List<Package>> findAllPackage(){
        List<Package> result = packageService.findAllPackage();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/packages/{id}")
    public ResponseEntity<List<Package>> findPackageById(@PathVariable int id){
        List<Package> result = packageService.findPackageById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/packages/sub-type-id/{subTypeId}")
    public ResponseEntity<List<Package>> findPackageBySubTypeId(@PathVariable int subTypeId){
        List<Package> result = packageService.findPackageBySubTypeId(subTypeId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/packages/subscription_type")
    public ResponseEntity<List<SubscriptionType>> findAllSubscriptionType(){
        List<SubscriptionType> result = packageService.findAllSubscriptionType();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
//    @GetMapping("/packages/validity")
//    public int findValidity(){
//        int result = packageService.findValidityById(10);
//        return result;
//    }
}
