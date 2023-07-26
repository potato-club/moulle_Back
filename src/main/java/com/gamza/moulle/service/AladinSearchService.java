package com.gamza.moulle.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AladinSearchService {

    private final WebClient webClient;
    private static final String BASE_URL = "http://www.aladin.co.kr/ttb/api/ItemLookUp.aspx";

    @Value("${aladin.key}")
    private String ttbKey;

    public AladinSearchService() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

    public String getBookByQuery(String isbn){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("ttbkey",ttbKey)
                        .queryParam("itemIdType","ISBN")
                        .queryParam("output","xml")
                        .queryParam("Version","20131101")
                        .queryParam("OptResult","ebookList,usedList,reviewList")
                        .queryParam("ItemId",isbn)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
}
