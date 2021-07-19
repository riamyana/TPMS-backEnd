package com.trasportManagement.transportservice.controller;


import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithAddress;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("members")
    public ResponseEntity<Member> addMember(@RequestBody(required=true) Member m) {
        Member memberList = memberService.addMember(m);
        return new ResponseEntity<>(memberList, HttpStatus.CREATED);
    }

    @PutMapping("members")
    public ResponseEntity<Member> updateMember(@RequestBody(required=true) Member m) {
        Member memberList = memberService.updateMember(m);
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("members/{memberId}")
    public ResponseEntity<Boolean> deleteMember(@PathVariable int memberId) {
        Boolean memberList = memberService.deleteMember(memberId);
        return new ResponseEntity<>(memberList, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> memberList = memberService.findMembers();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/members-memberType")
    public ResponseEntity<List<MemberWithMemberType>> getAllMembers() {
        List<MemberWithMemberType> memberList = memberService.findAllMembers();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<MemberWithMemberType>> getMemberById(@PathVariable int memberId) {
        List<MemberWithMemberType> memberList = memberService.findMemberById(memberId);
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/member/{userId}")
    public ResponseEntity<List<Member>> getMemberByUserId(@PathVariable int userId) {
        List<Member> memberList = memberService.findMemberByUserId(userId);
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/members/members-address")
    public ResponseEntity<List<MemberWithAddress>> getMemberWithAddress()
    {
        List<MemberWithAddress> addressList=memberService.findMemberWithAddress();
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/members/pass-request")
    public ResponseEntity<List<Member>> findMembersWithPassRequest() {
        List<Member> memberList = memberService.findMembersWithPassRequest();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("members/pass-request/status/{memberId}/{status}")
    public ResponseEntity<Integer> changePassRequestStatus(@PathVariable int memberId, @PathVariable int status, @RequestBody Member member) {
        int n = memberService.changePassRequestStatus(memberId, status, member.getDescription());
        return new ResponseEntity<>(n, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("members/pass-request/count/status/{status}")
    public ResponseEntity<Integer> coutsOfStatus(@PathVariable int status) {
        int n = memberService.coutsOfStatus(status);
        return new ResponseEntity<>(n, HttpStatus.OK);
    }
}
