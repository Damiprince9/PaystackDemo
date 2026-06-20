package com.example.PaystackDemo.service;

import com.example.PaystackDemo.model.*;
import com.example.PaystackDemo.model.PaymentTransaction;
import com.example.PaystackDemo.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {

    private final PaymentTransactionRepository repository;

    public TransactionService(PaymentTransactionRepository repository) {
        this.repository = repository;
    }


    public PaymentTransaction createPending(BigDecimal amount, String email) {

        PaymentTransaction tx = new PaymentTransaction();
        tx.setReference(UUID.randomUUID().toString());
        tx.setAmount(amount);
        tx.setEmail(email);
        tx.setStatus(PaymentStatus.PENDING);

        return repository.save(tx);
    }

    public void handleWebhook(Map<String, Object> payload) {

        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        String reference = (String) data.get("reference");

        String event = (String) payload.get("event");

        if (event.equals("charge.success")) {
            markSuccess(reference);
        }else {
            markFailed(reference);
        }

    }

    public void markSuccess(String reference) {
        try {
            repository.findById(reference)
                    .ifPresent(tx -> {
                        tx.setStatus(PaymentStatus.SUCCESS);
                        repository.save(tx);
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void markFailed(String reference){
        try{
            repository.findById(reference).ifPresent(tx -> {
                tx.setStatus(PaymentStatus.FAILED);
                repository.save(tx);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
