package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.MemberTypePackage;
import com.trasportManagement.transportservice.model.MemberTypeProof;
import com.trasportManagement.transportservice.service.MemberTypePackageService;
import com.trasportManagement.transportservice.service.MemberTypeProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberTypeProofController {

    @Autowired
    MemberTypeProofService memberTypeProofService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/member-type-proof")
    public ResponseEntity<MemberTypeProof> addMemberTypeProof(@RequestBody(required=true) MemberTypeProof mp) {
        MemberTypeProof result = memberTypeProofService.addMemberTypePackage(mp);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/member-type-proof/{id}")
    public ResponseEntity<MemberTypeProof> updateMemberTypeProof(@PathVariable int id, @RequestBody(required=true) MemberTypeProof mp) {
        MemberTypeProof result = memberTypeProofService.updateMemberTypePackage(id, mp);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/member-type-proof/{id}")
    public ResponseEntity<Boolean> deleteMemberTypeProof(@PathVariable int id) {
        Boolean result = memberTypeProofService.deleteMemberTypePackage(id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }
}
