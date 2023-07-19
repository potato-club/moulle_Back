package com.gamza.moulle.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {
       private String title;
       private String isbn;
       private String author;
//       private LocalDateTime pubDate;
       private int priceStandard;
//       private String cover;
       private String description;
       private String toc;
       private String link;
       private String seriesId;
}