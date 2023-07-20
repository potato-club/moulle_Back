package com.gamza.moulle.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "item")
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
