package com.trasportManagement.transportservice.controller;


import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("members")
    public ResponseEntity<Result<Member>> addMember(@RequestBody(required=true) Member m) {
        Result<Member> memberResult = memberService.addMember(m);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("members/{memberId}")
    public ResponseEntity<Result<Member>> updateMember(@PathVariable int memberId, @RequestBody(required=true) Member m) {
        Result<Member> memberResult = memberService.updateMember(memberId, m);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("members/{memberId}")
    public ResponseEntity<Result<Member>> deleteMember(@PathVariable int memberId) {
        Result<Member> memberResult = memberService.deleteMember(memberId);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/members")
    public ResponseEntity<Result<List<MemberWithMemberType>>> getMembers(){
        Result<List<MemberWithMemberType>> memberResult =memberService.findAllMembers();
        System.out.println(memberResult.getMessage());
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("members/{memberId}")
    public ResponseEntity<Result<MemberWithMemberType>> getMemberById(@PathVariable int memberId) {
        Result<MemberWithMemberType> memberResult = memberService.findMemberById(memberId);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

}
