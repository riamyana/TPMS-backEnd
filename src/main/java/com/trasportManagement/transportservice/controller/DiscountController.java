package com.trasportManagement.transportservice.controller;

import com.trasportManagement.transportservice.model.Discount;
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
    public ResponseEntity<Discount> addDiscount(@RequestBody(required=true) Discount d){
        Discount result = discountService.addDiscount(d);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/discount/{id}")
    public ResponseEntity<Boolean> deleteDiscount(@PathVariable int id){
        Boolean result = discountService.deleteDiscount(id);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/discount/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable int id, @RequestBody(required=true) Discount d) throws Exception {
        Discount result = discountService.updateDiscount(id, d);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/discounts/{packageId}")
    public ResponseEntity<List<Discount>> findAllDiscounts(@PathVariable int packageId){
        List<Discount> result = discountService.findAllDiscounts(packageId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
