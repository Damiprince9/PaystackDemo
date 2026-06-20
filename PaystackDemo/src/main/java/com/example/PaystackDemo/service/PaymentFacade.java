package com.example.PaystackDemo.service;

import com.example.PaystackDemo.configuration.PaystackClient;
import com.example.PaystackDemo.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentFacade {

    private final TransactionService transactionService;

    private final PaystackClient paystackClient;

    public PaymentFacade(TransactionService transactionService, PaystackClient paystackClient) {
        this.transactionService = transactionService;
        this.paystackClient = paystackClient;
    }


    public String startPayment(InitPaymentRequest request) {

        PaymentTransaction tx = transactionService.createPending(request.amount(), request.email());

        PaystackInitRequest paystackRequest = new PaystackInitRequest(
                        tx.getEmail(),
                        tx.getAmount().multiply(BigDecimal.valueOf(100)).intValue(),
                        tx.getReference()
                );

        PaystackInitData response = paystackClient.initializePayment(paystackRequest);

        return "Click this link to continue your payment" + "\n" +response.authorization_url();
    }


    public String startGetPayment(BigDecimal amount, String email) {

        PaymentTransaction tx =
                transactionService.createPending(amount, email);

        PaystackInitRequest paystackRequest =
                new PaystackInitRequest(
                        tx.getEmail(),
                        tx.getAmount().multiply(BigDecimal.valueOf(100)).intValue(),
                        tx.getReference()
                );

        PaystackInitData response =
                paystackClient.initializePayment(paystackRequest);


        return "Click the link to finish your payment " + response.authorization_url();
    }
}

