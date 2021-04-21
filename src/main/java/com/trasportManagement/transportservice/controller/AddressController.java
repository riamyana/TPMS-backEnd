package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Address;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/user/address")
public class AddressControllerForUser {

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<Result<Address>> addAddress(@RequestBody(required=true) Address a) {
        Result<Address> addressResult = addressService.addAddress(a);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Result<Address>> updateAddress(@PathVariable int addressId, @RequestBody(required=true) Address a) {
        Result<Address> addressResult = addressService.updateAddress(addressId, a);
        return new ResponseEntity<>(addressResult, HttpStatus.valueOf(addressResult.getCode()));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Result<Address>> deleteAddress(@PathVariable int addressId) {
        Result<Address> addressResult = addressService.deleteAddress(addressId);
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
