package com.gamza.moulle.dto.shelf;

import com.gamza.moulle.entity.ShelfEntity;
import lombok.Getter;

@Getter
public class ShelfResponseDto {
    private long id;
    private String shelfName;

    public ShelfResponseDto(ShelfEntity shelfEntity){
        this.id = shelfEntity.getId();
        this.shelfName = shelfEntity.getShelfName();
    }
}
