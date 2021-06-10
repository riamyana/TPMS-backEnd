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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberTypeController {

    @Autowired
    MemberTypeService memberTypeService;

    @GetMapping("/member-types")
    public ResponseEntity<List<MemberType>> getMemberTypes(){
        List<MemberType> memberTypeList =memberTypeService.findAllMemberTypes();
        return new ResponseEntity<>(memberTypeList, HttpStatus.OK);
    }

    @GetMapping(path="/member-types/{memberTypeId}")
    public ResponseEntity<List<MemberType>> getMemberTypeById(@PathVariable int memberTypeId) {
        List<MemberType> memberTypeList = memberTypeService.findMemberTypeById(memberTypeId);
        return new ResponseEntity<>(memberTypeList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/member-types")
    public ResponseEntity<MemberType> addMemberType(@RequestBody(required=true) MemberType m) {
        MemberType memberTypeList = memberTypeService.addMemberType(m);
        return new ResponseEntity<>(memberTypeList, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/member-types/{memberTypeId}")
    public ResponseEntity<MemberType> updateMemberType(@PathVariable int memberTypeId, @RequestBody(required=true) MemberType m) {
        MemberType memberTypeList = memberTypeService.updateMemberType(memberTypeId, m);
        return new ResponseEntity<>(memberTypeList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/member-types/{memberTypeId}")
    public ResponseEntity<Boolean> deleteMemberType(@PathVariable int memberTypeId) {
        Boolean memberTypeList = memberTypeService.deleteMemberType(memberTypeId);
        return new ResponseEntity<>(memberTypeList, HttpStatus.NO_CONTENT);
    }
}
