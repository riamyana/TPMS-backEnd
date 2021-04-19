package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.MemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/admin/memberType")
public class MemberTypeController {

    @Autowired
    MemberTypeService memberTypeService;

    @GetMapping
    public ResponseEntity<Result<List<MemberType>>> getAllMemberTypes(){
        Result<List<MemberType>> memberTypeResult =memberTypeService.findAllMemberTypes();
        System.out.println(memberTypeResult.getMessage());
        return new ResponseEntity<>(memberTypeResult, HttpStatus.valueOf(memberTypeResult.getCode()));
    }

    @GetMapping(path="/{memberTypeId}")
    public ResponseEntity<Result<MemberType>> getMemberTypeById(@PathVariable int memberTypeId) {
        Result<MemberType> memberTypeResult = memberTypeService.findMemberTypeById(memberTypeId);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.valueOf(memberTypeResult.getCode()));
    }

    @PostMapping
    public ResponseEntity<Result<MemberType>> addMemberType(@RequestBody(required=true) MemberType m) {
        Result<MemberType> memberTypeResult = memberTypeService.addMemberType(m);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.valueOf(memberTypeResult.getCode()));
    }

    @PutMapping("/{memberTypeId}")
    public ResponseEntity<Result<MemberType>> updateMemberType(@PathVariable int memberTypeId, @RequestBody(required=true) MemberType m) {
        Result<MemberType> memberTypeResult = memberTypeService.updateMemberType(memberTypeId, m);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.valueOf(memberTypeResult.getCode()));
    }

    @DeleteMapping("/{memberTypeId}")
    public ResponseEntity<Result<MemberType>> deleteMemberType(@PathVariable int memberTypeId) {
        Result<MemberType> memberTypeResult = memberTypeService.deleteMemberType(memberTypeId);
        return new ResponseEntity<>(memberTypeResult, HttpStatus.valueOf(memberTypeResult.getCode()));
    }
}
