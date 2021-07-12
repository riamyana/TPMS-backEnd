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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberProofsController {

    @Autowired
    MemberProofService memberProofService;

    @PostMapping("/member-proofs")
    public ResponseEntity<MemberProof> addMemberProof(@RequestParam("proofId") int proofId, @RequestParam("memberId") int memberId, @RequestParam("uidNo") String uidNo, @RequestParam("proofImage") MultipartFile multipartFile) {
        MemberProof memberProof = new MemberProof();
        memberProof.setProofId(proofId);
        memberProof.setMemberId(memberId);
        memberProof.setUidNo(uidNo);

        MemberProof memProofList = memberProofService.addMemberProof(memberProof, multipartFile);
        return new ResponseEntity<>(memProofList, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member-proofs")
    public ResponseEntity<List<MemberProofsWithMemberDetails>> getAllMembersProofDetails(){
        List<MemberProofsWithMemberDetails> memProofList =memberProofService.findAllMembersProofDetails();
        return new ResponseEntity<>(memProofList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member/{memberId}/member-proofs")
    public ResponseEntity<List<MemberProofsWithMemberDetails>> getMemberProofById(@PathVariable int memberId){
        List<MemberProofsWithMemberDetails> memProofList =memberProofService.findMemberProofById(memberId);
        return new ResponseEntity<>(memProofList, HttpStatus.OK);
    }
}
