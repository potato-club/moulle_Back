package com.gamza.moulle.controller;

import com.gamza.moulle.dto.aladin.SearchDetailDto;
import com.gamza.moulle.service.AladinSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class SearchController {

    private final AladinSearchService aladinSearchService;

    //책 자동저장
    @PostMapping("/auto-save")
    public String autoSaveBook(@RequestParam String isbn){
        return aladinSearchService.autoSaveBook(isbn);
    }
    //책 수동저장

    //검색어로 책 20개 보여주기 페이지네이션
//    @GetMapping("/search")
//    public
    //



}