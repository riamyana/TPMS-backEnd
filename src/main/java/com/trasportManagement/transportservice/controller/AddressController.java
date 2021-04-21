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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/member/member-address")
    public ResponseEntity<Result<Address>> addAddress(@RequestBody(required=true) Address a) {
        Result<Address> addressResult = addressService.addAddress(a);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/member/{memberId}/member-address/{addressId}")

    public ResponseEntity<Result<Address>> updateAddress(@PathVariable int addressId,@PathVariable int memberId,@RequestBody(required=true) Address a) {
        Result<Address> addressResult = addressService.updateAddress(addressId,memberId,a);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/member/{memberId}/member-address/{addressId}")
    public ResponseEntity<Result<Address>> deleteAddress(@PathVariable int addressId,@PathVariable int memberId) {
        Result<Address> addressResult = addressService.deleteAddress(addressId,memberId);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @GetMapping
    public ResponseEntity<Result<List<Address>>> getAllMembersAddress(){
        Result<List<Address>> addressResult =addressService.findAllMembersAddress();
        System.out.println(addressResult.getMessage());
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @GetMapping(path="/{memberId}")
    public ResponseEntity<Result<List<Address>>> getMemberAddressById(@PathVariable int memberId) {
        Result<List<Address>> addressResult = addressService.findMemberAddressById(memberId);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }
}
