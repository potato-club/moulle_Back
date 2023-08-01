package com.gamza.moulle.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class SearchDto {
       private String title;
       private String author;
       private String description;

       public SearchDto(String title,String author, String description) {
              this.title = title;
              this.author = author;
              this.description = description;
       }
}