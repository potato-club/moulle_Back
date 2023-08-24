package com.gamza.moulle.dto.mainpage;

import com.gamza.moulle.entity.BookshelfEntity;
import lombok.Getter;

@Getter
public class BookshelfResponseDto {
    private long id;
    private String shelfName;
    private int bookCount;

    public BookshelfResponseDto (BookshelfEntity bookshelfEntity){
        this.id = bookshelfEntity.getId();
        this.shelfName = bookshelfEntity.getShelfName();
        this.bookCount = bookshelfEntity.getBookCount();
    }
}
