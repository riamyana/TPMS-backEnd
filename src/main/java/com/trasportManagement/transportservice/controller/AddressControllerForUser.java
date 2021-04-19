package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Address;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
