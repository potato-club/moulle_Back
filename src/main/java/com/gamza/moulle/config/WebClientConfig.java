package com.gamza.moulle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder
                .baseUrl("http://www.aladin.co.kr/ttb/api/ItemList.aspx")
                .build();
    }
}
