package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Address;

import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/member/member-address")
    public ResponseEntity<Result<Address>> addAddress(@RequestBody(required=true) Address a) {
        Result<Address> addressResult = addressService.addAddress(a);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/member/{memberId}/member-address/{addressId}")

    public ResponseEntity<Result<Address>> updateAddress(@PathVariable int memberId,@PathVariable int addressId,@RequestBody(required=true) Address a) {
        Result<Address> addressResult = addressService.updateAddress(memberId,addressId,a);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/member/{memberId}/member-address/{addressId}")
    public ResponseEntity<Result<Address>> deleteAddress(@PathVariable int memberId,@PathVariable int addressId) {
        Result<Address> addressResult = addressService.deleteAddress(memberId,addressId);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member/member-address")
    public ResponseEntity<Result<List<Address>>> getAllMembersAddress(){
        Result<List<Address>> addressResult =addressService.findAllMembersAddress();
        System.out.println(addressResult.getMessage());
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member/member-address/{memberId}")
    public ResponseEntity<Result<List<Address>>> getMemberAddressById(@PathVariable int memberId) {
        Result<List<Address>> addressResult = addressService.findMemberAddressById(memberId);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/member/member-address/{memberId}")
//    public ResponseEntity<Result<List<MemberDetailWithAddress>>> getMemAddressById(@PathVariable int memberId) {
//        Result<List<MemberDetailWithAddress>> addressResult = addressService.findMemAddressById(memberId);
//        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
//    }
}
