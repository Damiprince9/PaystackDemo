package com.example.PaystackDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

//An annotation to filter out unknown properties from the json request
@JsonIgnoreProperties(ignoreUnknown = true)
public record InitPaymentRequest(
        BigDecimal amount,
        String email
) {}

