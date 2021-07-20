package com.trasportManagement.transportservice.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.trasportManagement.transportservice.response.Result;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PaymentController {

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody(required=true) Map<String, Object> data) throws Exception {
        Result<Order> result = new Result<>();
        int amt = Integer.parseInt(data.get("amount").toString());

        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_7qGLAEB07PuLai", "v50soIJ1jm4fjltmCYmmqlDr");

        JSONObject json = new JSONObject();
        json.put("amount", amt*100);
        json.put("currency", "INR");
        json.put("receipt", "txn_123456");

        Order order = razorpayClient.Orders.create(json);

        result.setData(order);
        result.setCode(200);

        return new ResponseEntity<>(order.toString(), HttpStatus.OK);
    }
}
