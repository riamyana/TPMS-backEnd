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
public class PassController {

    @Autowired
    PassService passService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/passes")
    public ResponseEntity<Result<Pass>> addPass(@RequestBody(required=true) Pass p) {
        Result<Pass> passResult = passService.addPass(p);
        return new ResponseEntity<>(passResult, HttpStatus.valueOf(passResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/passes/{passId}")
    public ResponseEntity<Pass> updatePass(@PathVariable int passId,   Pass p) {
        Pass passResult = passService.updatePass(passId,p);
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/passes/{passId}")
    public ResponseEntity<Boolean> deletePass(@PathVariable int passId) {
        Boolean passResult = passService.deletePass(passId);
        return new ResponseEntity<>(passResult, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/passes/{passId}/member")
    public ResponseEntity<List<PassWithMemberDetails>> getMemberPassById(@PathVariable int passId){
        List<PassWithMemberDetails> passResult =passService.findMemberPassById(passId);
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }

    @GetMapping("/passes/serial-no/{serialNo}/member")
    public ResponseEntity<List<PassWithMemberDetails>> getMemberPassBySerialNo(@PathVariable Long serialNo){
        List<PassWithMemberDetails> passResult =passService.findMemberPassBySerialNo(serialNo);
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/passes/member")
    public ResponseEntity<List<PassWithMemberDetails>> getMemberPass(){
        List<PassWithMemberDetails> passResult =passService.findAllMemberPasses();
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/passes")
    public ResponseEntity<List<Pass>> getPass(){
        List<Pass> passResult =passService.findAllPasses();
        return new ResponseEntity<>(passResult, HttpStatus.OK);
    }
}
