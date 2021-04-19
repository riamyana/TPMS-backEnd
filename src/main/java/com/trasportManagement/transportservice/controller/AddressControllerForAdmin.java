package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Address;
import com.trasportManagement.transportservice.model.Member;
import com.trasportManagement.transportservice.model.MemberWithMemberType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/admin/address")
public class AddressControllerForAdmin {

    @Autowired
    AddressService addressService;

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
