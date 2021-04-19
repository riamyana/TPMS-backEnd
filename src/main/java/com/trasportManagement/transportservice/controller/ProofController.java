package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofWithMemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.ProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/admin/proof")
public class ProofController {

    @Autowired
    ProofService proofService;

    @GetMapping
    public ResponseEntity<Result<List<ProofWithMemberType>>> getAllProofs(){
        Result<List<ProofWithMemberType>> proofResult =proofService.findAllProofs();
        System.out.println(proofResult.getMessage());
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }

    @PostMapping
    public ResponseEntity<Result<Proof>> addProof(@RequestBody(required=true) Proof p) {
        Result<Proof> proofResult = proofService.addProof(p);
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }

    @PutMapping("/{proofId}")
    public ResponseEntity<Result<Proof>> updateProof(@PathVariable int proofId, @RequestBody(required=true) Proof p) {
        Result<Proof> proofResult = proofService.updateProof(proofId, p);
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }

    @DeleteMapping("/{proofId}")
    public ResponseEntity<Result<Proof>> deleteProof(@PathVariable int proofId) {
        Result<Proof> proofResult = proofService.deleteProof(proofId);
        return new ResponseEntity<>(proofResult, HttpStatus.valueOf(proofResult.getCode()));
    }

}
