package com.gamza.moulle.controller;

import com.gamza.moulle.dto.SearchDto;
import com.gamza.moulle.entity.BookEntity;
import com.gamza.moulle.service.AladinApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("search")
public class SearchController {
    private final AladinApiService aladinApiService;

    @GetMapping("/")
    public ResponseEntity<?> search(@RequestParam String keyword) {
        List<BookEntity> books = new ArrayList<>();

        List<SearchDto.ItemDto> items = new ArrayList<>();
        for (BookEntity book : books) {
            SearchDto.ItemDto item = new SearchDto.ItemDto();
            item.setId(book.getId());
            item.setBookName(book.getBookName());
            item.setIsbnType(book.getIsbnType());
            item.setIsbn(book.getIsbn());
            item.setAuthor(book.getAuthor());
            item.setPublishedDate(book.getPublishedDate());
            item.setPrice(book.getPrice());
            item.setDescription(book.getDescription());
            item.setBookIndex(book.getBookIndex());
            item.setBookDetailLink(book.getBookDetailLink());
            item.setSeriesId(book.getSeriesId());
            items.add(item);
        }

        SearchDto searchDto = new SearchDto();
        searchDto.setTotal(items.size());
        searchDto.setItems(items);

        log.info("Search Result: {}", searchDto);

        return new ResponseEntity<>(searchDto, HttpStatus.OK);
    }
}