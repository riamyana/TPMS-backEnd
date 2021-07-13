package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Address;

import com.trasportManagement.transportservice.model.MemberWithAddress;
import com.trasportManagement.transportservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;


    @PostMapping("/member/member-address")
    public ResponseEntity<Address> addAddress(@RequestBody(required=true) Address a) {
        Address addressList = addressService.addAddress(a);
        return new ResponseEntity<>(addressList, HttpStatus.CREATED);
    }


    @PutMapping("/member/{memberId}/member-address/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable int memberId,@PathVariable int addressId,@RequestBody(required=true) Address a) {
        Address addressList = addressService.updateAddress(memberId,addressId,a);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }


    @DeleteMapping("/member/{memberId}/member-address/{addressId}")
    public ResponseEntity<Boolean> deleteAddress(@PathVariable int memberId,@PathVariable int addressId) {
        Boolean addressList = addressService.deleteAddress(memberId,addressId);
        return new ResponseEntity<>(addressList, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member/member-address")
    public ResponseEntity<List<Address>> getAllMembersAddress(){
        List<Address> addressResult =addressService.findAllMembersAddress();
        return new ResponseEntity<>(addressResult, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/member/member-address/{memberId}")
    public ResponseEntity<List<Address>> getMemberAddressById(@PathVariable int memberId) {
        List<Address> addressList = addressService.findMemberAddressById(memberId);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

}
