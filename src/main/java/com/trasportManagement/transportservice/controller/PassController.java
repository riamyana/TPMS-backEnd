package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Pass;
import com.trasportManagement.transportservice.model.PassWithMemberDetails;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PassController {

    @Autowired
    PassService passService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/passes")
    public ResponseEntity<Result<Pass>> addPass(@RequestBody(required=true) Pass p) {
        Result<Pass> passResult = passService.addPass(p);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/passes/{passId}")
    public ResponseEntity<Result<Pass>> updatePass(@PathVariable int passId, @RequestBody(required=true) Pass p) {
        Result<Pass> passResult = passService.updatePass(passId,p);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/passes/{passId}")
    public ResponseEntity<Result<Pass>> deletePass(@PathVariable int passId) {
        Result<Pass> passResult = passService.deletePass(passId);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @GetMapping("/passes/{passId}/member")
    public ResponseEntity<Result<List<PassWithMemberDetails>>> getMemberPassById(@PathVariable int passId){
        Result<List<PassWithMemberDetails>> passResult =passService.findMemberPassById(passId);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @GetMapping("/passes/serial-no/{serialNo}/member")
    public ResponseEntity<Result<List<PassWithMemberDetails>>> getMemberPassBySerialNo(@PathVariable Long serialNo){
        Result<List<PassWithMemberDetails>> passResult =passService.findMemberPassBySerialNo(serialNo);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/passes/member")
    public ResponseEntity<Result<List<PassWithMemberDetails>>> getMemberPass(){
        Result<List<PassWithMemberDetails>> passResult =passService.findAllMemberPasses();
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/passes")
    public ResponseEntity<Result<List<Pass>>> getPass(){
        Result<List<Pass>> passResult =passService.findAllPasses();
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }
}
