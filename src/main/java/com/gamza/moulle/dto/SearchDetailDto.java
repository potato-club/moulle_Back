package com.gamza.moulle.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDetailDto {
    List<Items> items = new ArrayList<>();

    public static class Items{
        public String title;
        public String isbn;
        public String author;
        //       private LocalDateTime pubDate;
        public int priceStandard;
        //       private String cover;
        public String description;
        public String toc;
        public String link;
        public String seriesId;
    }
}
