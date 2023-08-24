package com.gamza.moulle.service;

import com.gamza.moulle.dto.aladin.SearchDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AladinSearchService {

    private final WebClient aladinWebClient;

    @Value("${aladin.key}")
    private String ttbKey;

    public String autoSaveBook(String isbn) {
        //사진 처리 해야함

        //동기 통신으로 전환
        SearchDetailDto searchDetailDto = aladinWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("ttbkey", ttbKey)
                        .queryParam("itemIdType", "ISBN13")
                        .queryParam("output", "JS")
                        .queryParam("Version", "20131101")
                        .queryParam("OptResult", "ebookList,ratingInfo,reviewList")
                        .queryParam("ItemId", isbn)
                        .build())
                .retrieve()
                .bodyToMono(SearchDetailDto.class)
                .block();
        //유저책 디비저장

        return "good";
    }

}
