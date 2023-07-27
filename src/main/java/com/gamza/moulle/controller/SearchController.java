package com.gamza.moulle.controller;

import com.gamza.moulle.dto.SearchDetailDto;
import com.gamza.moulle.dto.SearchDto;
import com.gamza.moulle.service.AladinSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final AladinSearchService aladinSearchService;

    @GetMapping("/detail")
    public Mono<SearchDetailDto> getBookInfo(@RequestParam String isbn){
        return aladinSearchService.getBookByQuery(isbn);
    }

}