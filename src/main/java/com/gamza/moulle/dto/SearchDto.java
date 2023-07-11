package com.gamza.moulle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {

    public Integer total; // 한번에 배열에 담아서 출력
    List<Items> items = new ArrayList<>();

    static class Items{
        private String bookName;
        private String author;
        private String isbn;
        private LocalDateTime publishedDate;
        private int price;
        private String description;
        private String bookIndex;
        private String bookDetailLink;
        private String seriesId;


    }
}
