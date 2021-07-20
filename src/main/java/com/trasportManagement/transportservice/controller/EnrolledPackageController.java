package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.EnrolledMemberPackage;
import com.trasportManagement.transportservice.model.EnrolledPackage;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EnrolledPackageController {

    @Autowired
    EnrolledPackageService enrolledPackageService;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping("/enrolled-packages")
    public ResponseEntity<EnrolledPackage> addEnrolledPackage(@RequestBody(required=true) EnrolledPackage e) {
        EnrolledPackage enrolledResult = enrolledPackageService.addEnrolledPackage(e);
        return new ResponseEntity<>(enrolledResult, HttpStatus.CREATED);
    }

    @GetMapping("/enrolled-packages")
    public ResponseEntity<List<EnrolledPackage>> findAllEnrolledPackage() {

        List<EnrolledPackage> enrolledResult = enrolledPackageService.findAllEnrolledPackage();
        return new ResponseEntity<>(enrolledResult, HttpStatus.OK);

    }

    @GetMapping("/enrolled-packages/{passId}")
    public ResponseEntity<List<EnrolledPackage>> findEnrolledPackageByPassId(@PathVariable int passId) {

        List<EnrolledPackage> enrolledResult = enrolledPackageService.findEnrolledPackageByPassId(passId);
        return new ResponseEntity<>(enrolledResult, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PutMapping("/enrolled-packages/isActive")
    public ResponseEntity<EnrolledPackage> updateEnrolledPackage(@RequestBody(required=true) EnrolledPackage e) {
        EnrolledPackage enrolledResult = enrolledPackageService.updateIsActive(e);
        return new ResponseEntity<>(enrolledResult, HttpStatus.OK);
    }


    @GetMapping("/enrolled-package/{passId}")
    public ResponseEntity<List<EnrolledMemberPackage>> findEnrolledPkgByPassId(@PathVariable int passId) {

        List<EnrolledMemberPackage> enrolledResult = enrolledPackageService.findEnrolledPkgByPassId(passId);
        return new ResponseEntity<>(enrolledResult, HttpStatus.OK);

    }


//
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @DeleteMapping("/enrolled-packages/{id}")
//    public ResponseEntity<Result<EnrolledPackage>> deleteEnrolledPackage(@PathVariable int id) {
//        Result<EnrolledPackage> enrolledResult = enrolledPackageService.deleteEnrolledPackage(id);
//        return new ResponseEntity<>(enrolledResult, HttpStatus.valueOf(enrolledResult.getCode()));
//    }

//    @GetMapping("/enrolled-packages/demo")
//    public void demo(){
//        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date curDate = new Date();
//        System.out.println("date"+df.format(curDate));
//    }
}
