package com.gamza.moulle.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDetailDto {
    private List<Map<String, String>> items = new ArrayList<>();

}
