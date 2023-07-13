//package com.gamza.moulle.service;
//
//import com.gamza.moulle.dto.SearchDto;
//import com.gamza.moulle.repository.BookRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.nio.charset.StandardCharsets;
//import java.util.Collections;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class SearchService {
//
//    private final BookRepository bookRepository;
//    private final WebClient webClient;
//
//    @Value("${aladin.url}")
//    private String pageUrl;
//    private final String Search_URL = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?MaxResults=10&start=1&Version=20131101&ttbkey=ttbahb03272343002";
//
//    public SearchDto searchBooks(String keyword) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<String> httpEntity = new HttpEntity<>(getHeaders());
//
//        URI targetUrl = UriComponentsBuilder
//                .fromUriString(Search_URL)
//                .queryParam("Query",keyword)
//                .build()
//                .encode(StandardCharsets.UTF_8)
//                .toUri();
//
//        ResponseEntity<SearchDto> response = restTemplate.exchange(targetUrl, HttpMethod.GET,httpEntity, SearchDto.class);
//        return response.getBody();
//    }
//
//    private HttpHeaders getHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        return headers;
//    }
//
//}
//
//
//
