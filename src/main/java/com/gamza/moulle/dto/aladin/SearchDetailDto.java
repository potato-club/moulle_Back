package com.gamza.moulle.dto.aladin;

import com.gamza.moulle.dto.aladin.Item;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchDetailDto {
    private List<Item> item;

}
