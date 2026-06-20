package com.example.PaystackDemo.model;

public record PaystackInitRequest(
        String email,
        Integer amount,
        String reference
) {
}

