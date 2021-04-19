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
@RequestMapping(path="/user/member")
public class MemberControllerForUser {

    @Autowired
    MemberService memberService;

    @PostMapping
    public ResponseEntity<Result<Member>> addMember(@RequestBody(required=true) Member m) {
        Result<Member> memberResult = memberService.addMember(m);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Result<Member>> updateMember(@PathVariable int memberId, @RequestBody(required=true) Member m) {
        Result<Member> memberResult = memberService.updateMember(memberId, m);
        return new ResponseEntity<>(memberResult, HttpStatus.valueOf(memberResult.getCode()));
    }
}
