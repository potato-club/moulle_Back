package com.gamza.moulle.service;

import com.gamza.moulle.dto.aladin.SearchDetailDto;
import com.gamza.moulle.dto.shelf.BookSaveRequestDto;
import com.gamza.moulle.entity.ShelfEntity;
import com.gamza.moulle.entity.UserEntity;
import com.gamza.moulle.jwt.JwtProvider;
import com.gamza.moulle.repository.ShelfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AladinSearchService {

    private final WebClient aladinWebClient;
    private final JwtProvider jwtProvider;
    private final ShelfRepository shelfRepository;

    @Value("${aladin.key}")
    private String ttbKey;

    public String autoSaveBook(BookSaveRequestDto bookSaveRequestDto, HttpServletRequest request) {
        UserEntity userEntity = jwtProvider.findByUserOnToken(request);

        //사진 처리 해야함

        //동기 통신으로 전환
        SearchDetailDto searchDetailDto = aladinWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("ttbkey", ttbKey)
                        .queryParam("itemIdType", "ISBN13")
                        .queryParam("output", "JS")
                        .queryParam("Version", "20131101")
                        .queryParam("OptResult", "ebookList,ratingInfo,reviewList")
                        .queryParam("ItemId", bookSaveRequestDto.getIsbnCode())
                        .build())
                .retrieve()
                .bodyToMono(SearchDetailDto.class)
                .block();
        //유저책장 가져오기
        ShelfEntity shelfEntity = shelfRepository.findByUserEntityAndShelfName(userEntity, bookSaveRequestDto.getShelfName());


        return "good";
    }

}
