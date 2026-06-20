package com.example.PaystackDemo.configuration;

import com.example.PaystackDemo.model.PaystackInitData;
import com.example.PaystackDemo.model.PaystackInitRequest;
import com.example.PaystackDemo.model.PaystackInitResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PaystackClient {

    private final WebClient webClient;

    public PaystackClient(WebClient paystackWebClient) {
        this.webClient = paystackWebClient;
    }

    public PaystackInitData initializePayment(PaystackInitRequest request) {

        //sends a post request to PayStack's api
        return (webClient.post()
                        .uri("/transaction/initialize")
                //The RequestBody sent in the post request
                        .bodyValue(request)
                //Executes the request
                        .retrieve()
                //Converts the JSON response to a PaystackInitResponse instance
                        .bodyToMono(PaystackInitResponse.class)
                //Waits synchronously for the response
                        .block())
                //Returns the data section of the response
                .data();
    }
}

