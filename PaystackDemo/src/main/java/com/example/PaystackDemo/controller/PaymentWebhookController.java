package com.example.PaystackDemo.controller;

import com.example.PaystackDemo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentWebhookController {

    private final TransactionService paymentService;

    public PaymentWebhookController(TransactionService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> receiveWebhook(@RequestBody Map<String, Object> payload) {

        paymentService.handleWebhook(payload);

        return new ResponseEntity<>("Payment verified Successfully", HttpStatus.FOUND);

        //return ResponseEntity.ok().build();

    }
}