package com.example.PaystackDemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class PaystackClientConfig {

    @Value("${paystack.base-url}")
    private String baseUrl;

    @Value("${paystack.secret-key}")
    private String secretKey;

    //A bean method to configure our webClient in this application
    @Bean
    WebClient paystackWebClient() {

        return WebClient.builder()
                //this is the pay stack api that the webclient will communicate with
                .baseUrl(baseUrl)
                //the secret key or authorization header that the above api will request for
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + secretKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
