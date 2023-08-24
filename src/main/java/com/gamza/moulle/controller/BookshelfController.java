package com.gamza.moulle.controller;

import com.gamza.moulle.dto.mainpage.BookshelfResponseDto;
import com.gamza.moulle.service.UserBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class BookshelfController {
    private final UserBookService userBookService;

    // 수납함 리스트 리턴
    public List<BookshelfResponseDto> getBookshelfList (HttpServletRequest request){
        return userBookService.getBookshelfList(request);
    }
    //수납함 추가, 업데이트, 삭제

    // 업적 정리
}
