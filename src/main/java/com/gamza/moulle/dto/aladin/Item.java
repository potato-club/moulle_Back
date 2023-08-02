package com.gamza.moulle.dto.aladin;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Item {
       private String title;
       private String link;
       private String author;
       private LocalDate pubDate;
       private String description;
       private String isbn13;
       private int priceStandard;
       private String cover;
       private int categoryId;
       private String categoryName;
       private SeriesInfo seriesInfo;
       private SubInfo subInfo;
}