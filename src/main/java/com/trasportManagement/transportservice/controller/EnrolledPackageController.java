package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.EnrolledPackage;
import com.trasportManagement.transportservice.model.TransportMode;
import com.trasportManagement.transportservice.model.TransportPackage;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.EnrolledPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EnrolledPackageController {

    @Autowired
    EnrolledPackageService enrolledPackageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/enrolled-packages")
    public ResponseEntity<Result<EnrolledPackage>> addEnrolledPackage(@RequestBody(required=true) EnrolledPackage e) {
        Result<EnrolledPackage> enrolledResult = enrolledPackageService.addEnrolledPackage(e);
        return new ResponseEntity<>(enrolledResult, HttpStatus.valueOf(enrolledResult.getCode()));
    }

    @GetMapping("/enrolled-packages")
    public ResponseEntity<Result<List<EnrolledPackage>>> findAllEnrolledPackage() {

        Result<List<EnrolledPackage>> enrolledResult = enrolledPackageService.findAllEnrolledPackage();
        return new ResponseEntity<>(enrolledResult, HttpStatus.valueOf(enrolledResult.getCode()));

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/enrolled-packages/{id}")
    public ResponseEntity<Result<EnrolledPackage>> updateEnrolledPackage(@PathVariable int id, @RequestBody(required=true) EnrolledPackage e) {
        Result<EnrolledPackage> enrolledResult = enrolledPackageService.updateEnrolledPackage(id,e);
        return new ResponseEntity<>(enrolledResult, HttpStatus.valueOf(enrolledResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/enrolled-packages/{id}")
    public ResponseEntity<Result<EnrolledPackage>> deleteEnrolledPackage(@PathVariable int id) {
        Result<EnrolledPackage> enrolledResult = enrolledPackageService.deleteEnrolledPackage(id);
        return new ResponseEntity<>(enrolledResult, HttpStatus.valueOf(enrolledResult.getCode()));
    }

//    @GetMapping("/enrolled-packages/demo")
//    public void demo(){
//        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date curDate = new Date();
//        System.out.println("date"+df.format(curDate));
//    }
}
