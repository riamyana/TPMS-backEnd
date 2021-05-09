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
    public ResponseEntity<Address> addAddress(@RequestBody(required=true) Address a) {
        Address addressResult = addressService.addAddress(a);
        return new ResponseEntity<>(addressResult, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/member/{memberId}/member-address/{addressId}")

    public ResponseEntity<Address> updateAddress(@PathVariable int memberId,@PathVariable int addressId,@RequestBody(required=true) Address a) {
        Address addressResult = addressService.updateAddress(memberId,addressId,a);
        return new ResponseEntity<>(addressResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/member/{memberId}/member-address/{addressId}")
    public ResponseEntity<Boolean> deleteAddress(@PathVariable int memberId,@PathVariable int addressId) {
        Boolean addressResult = addressService.deleteAddress(memberId,addressId);
        return new ResponseEntity<>(addressResult, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Address>> getAllMembersAddress(){
        List<Address> addressResult =addressService.findAllMembersAddress();
//        System.out.println(addressResult.getMessage());
        return new ResponseEntity<>(addressResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path="/{memberId}")
    public ResponseEntity<List<Address>> getMemberAddressById(@PathVariable int memberId) {
        List<Address> addressResult = addressService.findMemberAddressById(memberId);
        return new ResponseEntity<>(addressResult, HttpStatus.OK);
    }
}
