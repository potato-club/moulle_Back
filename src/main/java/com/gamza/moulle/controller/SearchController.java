package com.gamza.moulle.controller;

import com.gamza.moulle.dto.shelf.BookSaveRequestDto;
import com.gamza.moulle.service.AladinSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class SearchController {

    private final AladinSearchService aladinSearchService;

    //책 자동저장
    @PostMapping("/auto-save")
    public String autoSaveBook(@RequestBody BookSaveRequestDto bookSaveRequestDto, HttpServletRequest request){
        return aladinSearchService.autoSaveBook(bookSaveRequestDto, request);
    }
    //책 수동저장

    //검색어로 책 20개 보여주기 페이지네이션
//    @GetMapping("/search")
//    public
    //



}