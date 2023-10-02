package com.gamza.moulle.dto.shelf;

import com.gamza.moulle.entity.BookEntity;

public class BookSimpleInfoResponseDto {
    private Long id;
    private String bookName;
    private String imageLink;
    private String author;

    public BookSimpleInfoResponseDto (BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.bookName = bookEntity.getBookName();
        this.imageLink = bookEntity.getImageLink();
        this.author = bookEntity.getAuthor();
    }
}
