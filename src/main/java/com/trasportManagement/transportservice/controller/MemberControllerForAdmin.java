package com.trasportManagement.transportservice.controller;


import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/admin/member")

public class MemberControllerForAdmin {
    @Autowired
    MemberService memberService;

    @GetMapping
    public ResponseEntity<Result<List<MemberWithMemberType>>> getAllMembers(){
        Result<List<MemberWithMemberType>> memberResult =memberService.findAllMembers();
        System.out.println(memberResult.getMessage());
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

    @GetMapping(path="/{memberId}")
    public ResponseEntity<Result<MemberWithMemberType>> getMemberById(@PathVariable int memberId) {
        Result<MemberWithMemberType> memberResult = memberService.findMemberById(memberId);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Result<Member>> deleteMember(@PathVariable int memberId) {
        Result<Member> memberResult = memberService.deleteMember(memberId);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

}
