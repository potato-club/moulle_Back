package com.gamza.moulle.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {
       private String title;
       private String author;
       private String description;
}