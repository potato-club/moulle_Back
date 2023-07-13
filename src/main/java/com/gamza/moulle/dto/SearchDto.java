package com.gamza.moulle.dto;

import com.gamza.moulle.enums.ISBNType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
        private Integer total;
        private List<ItemDto> items;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ItemDto {
                private Long id;
                private String bookName;
                private ISBNType isbnType;
                private int isbn;
                private String author;
                private LocalDateTime publishedDate;
                private int price;
                private String description;
                private String bookIndex;
                private String bookDetailLink;
                private String seriesId;
        }
}