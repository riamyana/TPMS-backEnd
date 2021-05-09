package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.MemberProof;
import com.trasportManagement.transportservice.model.MemberProofsWithMemberDetails;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.MemberProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberProofsController {

    @Autowired
    MemberProofService memberProofService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/member-proofs")
    public ResponseEntity<MemberProof> addMemberProof(@RequestBody(required=true) MemberProof mp) {
        MemberProof memProofResult = memberProofService.addMemberProof(mp);
        return new ResponseEntity<>(memProofResult, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member-proofs")
    public ResponseEntity<List<MemberProofsWithMemberDetails>> getAllMembersProofDetails(){
        List<MemberProofsWithMemberDetails> proofDetailResult =memberProofService.findAllMembersProofDetails();
//        System.out.println(proofDetailResult.getMessage());
        return new ResponseEntity<>(proofDetailResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("hasAuthority")
    public ResponseEntity<List<MemberProofsWithMemberDetails>> getMemberProofById(@PathVariable int memberId){
        List<MemberProofsWithMemberDetails> proofDetailResult =memberProofService.findMemberProofById(memberId);
//        System.out.println(proofDetailResult.getMessage());
        return new ResponseEntity<>(proofDetailResult, HttpStatus.OK);
    }
}
