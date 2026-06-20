package com.example.PaystackDemo.model;

public record PaystackInitResponse(
        boolean status,
        PaystackInitData data
) {}