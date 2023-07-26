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
        public String author;
        public String description;
    }
}
