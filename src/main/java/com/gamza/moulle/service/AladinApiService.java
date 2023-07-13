package com.gamza.moulle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AladinApiService {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

    public AladinApiService(@Autowired RestTemplate restTemplate,
                            @Value("${aladin.url}") String apiUrl,
                            @Value("${aladin.apiKey}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public ResponseEntity<String> searchBooksByKeyword(String keyword) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("ttbkey", apiKey)
                .queryParam("itemIdType", "ISBN13")
                .queryParam("output", "json")
                .queryParam("Version", "20131101")
                .queryParam("QueryType", "Title")
                .queryParam("MaxResults", 10)
                .queryParam("SearchTarget", "Book")
                .queryParam("start", 1)
                .queryParam("Keyword", keyword)
                .build()
                .toUriString();

        return restTemplate.getForEntity(url, String.class);
    }
}
