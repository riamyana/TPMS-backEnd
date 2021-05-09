package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.BitSet;
import java.util.List;

@RestController
public class MemberTypeController {

    @Autowired
    MemberTypeService memberTypeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member-types")
    public ResponseEntity<List<MemberType>> getMemberTypes(){
        List<MemberType> memberTypeResult =memberTypeService.findAllMemberTypes();
//        System.out.println(memberTypeResult.getMessage());
        return new ResponseEntity<>(memberTypeResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path="/member-types/{memberTypeId}")
    public ResponseEntity<List<MemberType>> getMemberTypeById(@PathVariable int memberTypeId) {
        List<MemberType> memberTypeResult = memberTypeService.findMemberTypeById(memberTypeId);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/member-types")
    public ResponseEntity<MemberType> addMemberType(@RequestBody(required=true) MemberType m) {
        MemberType memberTypeResult = memberTypeService.addMemberType(m);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/member-types/{memberTypeId}")
    public ResponseEntity<MemberType> updateMemberType(@PathVariable int memberTypeId, @RequestBody(required=true) MemberType m) {
        MemberType memberTypeResult = memberTypeService.updateMemberType(memberTypeId, m);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{memberTypeId}")
    public ResponseEntity<Boolean> deleteMemberType(@PathVariable int memberTypeId) {
        Boolean memberTypeResult = memberTypeService.deleteMemberType(memberTypeId);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.NO_CONTENT);
    }
}
