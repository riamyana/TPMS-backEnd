package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Discount;
import com.trasportManagement.transportservice.model.SubscriptionType;
import com.trasportManagement.transportservice.response.Result;
import com.trasportManagement.transportservice.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/discount")
    public ResponseEntity<Result<Discount>> addDiscount(@RequestBody(required=true) Discount d){
        Result<Discount> result = discountService.addDiscount(d);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/discount/{id}")
    public ResponseEntity<Result<Discount>> updateDiscount(@PathVariable int id, @RequestBody(required=true) Discount d){
        Result<Discount> result = discountService.updateDiscount(id,d);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/discount/{id}")
    public ResponseEntity<Result<Discount>> updateDiscount(@PathVariable int id){
        Result<Discount> result = discountService.deleteDiscount(id);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/discount/{packageId}")
    public ResponseEntity<Result<List<Discount>>> findAllDiscount(@PathVariable int packageId){
        Result<List<Discount>> result = discountService.findAllDiscounts(packageId);
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }
}
