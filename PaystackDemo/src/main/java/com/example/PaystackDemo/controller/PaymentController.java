package com.example.PaystackDemo.controller;

import com.example.PaystackDemo.model.InitPaymentRequest;
import com.example.PaystackDemo.service.PaymentFacade;
import com.example.PaystackDemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
//@RequestMapping("/api/payments")
public class PaymentController {

    private String url;

    private final PaymentFacade paymentFacade;

    public PaymentController(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    @PostMapping("/init")
    public ResponseEntity<String> initialize(@RequestBody InitPaymentRequest request) {
        url = paymentFacade.startPayment(request);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/init")
    public ResponseEntity<String> getPayment(){
        url = paymentFacade.startGetPayment(
                BigDecimal.valueOf(40000),
                "adewaledamilola378@gmail.com");
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}


