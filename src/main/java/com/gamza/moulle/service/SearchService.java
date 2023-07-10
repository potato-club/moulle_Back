package com.gamza.moulle.service;

import com.gamza.moulle.dto.SearchDto;
import com.gamza.moulle.entity.BookEntity;
import com.gamza.moulle.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final BookRepository bookRepository;

    RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public void fetchDataFromApi() {
        List<String> isbnCodeList = List.of("9791133487516");
        List<BookEntity> bookList = new ArrayList<>();
        for(String isbnCode : isbnCodeList) {
            String pageUrl = "https://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey=ttbahb03272343002&itemIdType=ISBN&ItemId=" + isbnCode + "&output=js&Version=20131101&OptResult=packing";
            SearchDto dto = restTemplate.getForObject(pageUrl, SearchDto.class);
            SearchDto.Item
        }
    }



}



