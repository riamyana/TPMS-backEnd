package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.ProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProofController {

    @Autowired
    ProofService proofService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/proofs")
    public ResponseEntity<Result<Proof>> addProof(@RequestBody(required=true) Proof p) {
        Result<Proof> proofResult = proofService.addProof(p);
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }


    @GetMapping("/proofs")
    public ResponseEntity<Result<List<ProofWithMemberType>>> getAllProofs(){
        Result<List<ProofWithMemberType>> proofResult =proofService.findAllProofs();
        System.out.println(proofResult.getMessage());
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }


    @GetMapping("/proofs/member-types/{memberTypeId}")
    public ResponseEntity<Result<List<Proof>>> getProofsByMemberTypeId(@PathVariable int memberTypeId){
        Result<List<Proof>> proofResult=proofService.findProofsByMemberTypeId(memberTypeId);
        return new ResponseEntity<>(proofResult,HttpStatus.valueOf(proofResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/proofs/{proofId}")
    public ResponseEntity<Result<Proof>> updateProof(@PathVariable int proofId, @RequestBody(required=true) Proof p) {
        Result<Proof> proofResult = proofService.updateProof(proofId, p);
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/proofs/{proofId}")
    public ResponseEntity<Result<Proof>> deleteProof(@PathVariable int proofId) {
        Result<Proof> proofResult = proofService.deleteProof(proofId);
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }

}
