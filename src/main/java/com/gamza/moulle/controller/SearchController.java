package com.gamza.moulle.controller;

import com.gamza.moulle.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/detail")
    public ResponseEntity<?> searchDetail(@RequestParam String isbn){
        log.info("[Request] search detail");
        return new ResponseEntity<>(searchService.searchDetail(isbn), HttpStatus.OK);
    }

}