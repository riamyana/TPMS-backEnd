package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Proof;
import com.trasportManagement.transportservice.model.ProofRequirement;
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
    public ResponseEntity<List<Proof>> getAllProofs(){
        List<Proof> proofList = proofService.findAllProofs();
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/proofs/requirement")
    public ResponseEntity<ProofRequirement> addProofRequirement(@RequestBody(required=true) ProofRequirement p) {
        ProofRequirement proofList = proofService.addProofRequirement(p);
        return new ResponseEntity<>(proofList, HttpStatus.CREATED);
    }

    @GetMapping("/proofs/requirement")
    public ResponseEntity<List<ProofRequirement>> findAllProofRequirement(){
        List<ProofRequirement> proofList = proofService.findAllProofRequirement();
        return new ResponseEntity<>(proofList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/proofs/requirement/{id}")
    public ResponseEntity<ProofRequirement> updateProofRequirement(@PathVariable int id, @RequestBody(required=true) ProofRequirement p) {
        ProofRequirement proofResult = proofService.updateProofRequirement(id, p);
        return new ResponseEntity<>(proofResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/proofs/requirement/{id}")
    public ResponseEntity<Boolean> deleteProofRequirement(@PathVariable int id) {
        Boolean proofList = proofService.deleteProofRequirement(id);
        return new ResponseEntity<>(proofList, HttpStatus.NO_CONTENT);
    }

}
