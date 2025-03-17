package com.IT0033.weddingApp.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfigIT0033 {

    @Bean
    public WebClient vendorWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8070/api/vendors") // Replace with Vendor Service URL
                .build();
    }

    @Bean
    public WebClient taskServiceWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8095") // Replace with the actual Task Service URL
                .build();
    }
}
