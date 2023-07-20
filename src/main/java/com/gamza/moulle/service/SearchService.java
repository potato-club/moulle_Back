package com.gamza.moulle.service;

import com.gamza.moulle.dto.SearchDetailDto;
import com.gamza.moulle.dto.SearchDto;
import com.gamza.moulle.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final BookRepository bookRepository;

    @Value("${aladin.url}")
    private String pageUrl;

    public SearchDto searchDetail(String isbn){
        List<SearchDetailDto.Items> items = searchInfo(isbn).getItems();
        if (items.isEmpty()) //검색 결과가 없거나, isbn 형식이 잘못됐을 때
            throw new IllegalArgumentException("isbn = " + isbn);

        SearchDetailDto.Items item = items.get(0);
//        String[] str = isbn.split(" ");
//        Integer page = pageInfo(str[1]); // 13자리 isbn
        return SearchDto.builder()
                .title(item.title)
                .author(item.author)
                .priceStandard(item.priceStandard)
                .description(item.description)
                .build();
    }

        private SearchDetailDto searchInfo(String isbn){
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = pageUrl.replace("{isbn}", isbn); // URL에 실제 ISBN 값을 넣어주기 위해 치환합니다.

            // XML 형식의 응답을 처리하기 위한 HttpMessageConverter 추가
            MappingJackson2XmlHttpMessageConverter xmlConverter = new MappingJackson2XmlHttpMessageConverter();
            xmlConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_XML));
            restTemplate.getMessageConverters().add(xmlConverter);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.TEXT_XML_VALUE);

            URI targetUrl = UriComponentsBuilder
                    .fromUriString(apiUrl)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUri();

            System.out.println(apiUrl); // 여기까지는 제대로 요구하는 데이터가 나오네
//            SearchDetailDto detailDto = restTemplate.exchange(targetUrl, HttpMethod.GET, new HttpEntity<>(headers), SearchDetailDto.class).getBody();
            ResponseEntity<List<SearchDetailDto>> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<SearchDetailDto>>() {
            });
            List<SearchDetailDto> list = response.getBody();
//
//            ResponseEntity<List<ResultClass>> response = restTemplate.exchange("url",HttpMethod.GET, null, new ParameterizedTypeReference<List<ResultClass>>() {});
//            List<ResultClass> list = response.getBody();
        return null; // 지금 현재 단일객체로 데이터가 전송되는 restTemplate 방식떄문에 개빢침 이거 다시 와서 해보자
    }


    private Integer pageInfo(String isbn){
        RestTemplate restTemplate = new RestTemplate();
        Map obj = restTemplate.getForObject(pageUrl,Map.class,isbn);
        Integer page = null;
        if(Objects.requireNonNull(obj).get("errorCode") ==null)
            page = (Integer) ((HashMap) ((HashMap) ((List) obj.get("item")).get(0)).get("subInfo")).get("itemPage");
        return page;
    }

}



