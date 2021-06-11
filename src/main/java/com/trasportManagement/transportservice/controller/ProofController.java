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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProofController {

    @Autowired
    ProofService proofService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/proofs")
    public ResponseEntity<Proof> addProof(@RequestBody(required=true) Proof p) {
        Proof proofList = proofService.addProof(p);
        return new ResponseEntity<>(proofList, HttpStatus.CREATED);
    }

    @GetMapping("/proofs")
    public ResponseEntity<List<ProofWithMemberType>> getAllProofs(){
        List<ProofWithMemberType> proofList = proofService.findAllProofs();
        return new ResponseEntity<>(proofList, HttpStatus.OK);
    }


    @GetMapping("/proofs/member-types/{memberTypeId}")
    public ResponseEntity<List<Proof>> getProofsByMemberTypeId(@PathVariable int memberTypeId){
        List<Proof> proofList = proofService.findProofsByMemberTypeId(memberTypeId);
        return new ResponseEntity<>(proofList,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/proofs/{proofId}")
    public ResponseEntity<Proof> updateProof(@PathVariable int proofId, @RequestBody(required=true) Proof p) {
        Proof proofResult = proofService.updateProof(proofId, p);
        return new ResponseEntity<>(proofResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/proofs/{proofId}")
    public ResponseEntity<Boolean> deleteProof(@PathVariable int proofId) {
        Boolean proofList = proofService.deleteProof(proofId);
        return new ResponseEntity<>(proofList, HttpStatus.NO_CONTENT);
    }

}
