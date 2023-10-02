package com.gamza.moulle.controller;

import com.gamza.moulle.dto.shelf.BookSimpleInfoResponseDto;
import com.gamza.moulle.dto.shelf.ShelfResponseDto;
import com.gamza.moulle.service.UserBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("my-book")
public class BookshelfController {
    private final UserBookService userBookService;
    //=====================책장 관련========================
    // 수납함 리스트 리턴
    @GetMapping("/shelf")
    public List<ShelfResponseDto> getShelfList (HttpServletRequest request){
        return userBookService.getShelfList(request);
    }
    //수납함 추가, 업데이트, 삭제
    @PostMapping("/shelf")
    public void addShelf (@RequestBody String shelfName, HttpServletRequest request) {
        userBookService.addShelf(shelfName,request);
    }
    @PutMapping("/shelf/{id}")
    public void updateShelf(@PathVariable Long id,@RequestBody String shelfName, HttpServletRequest request) {
        userBookService.updateShelf(id,shelfName,request);
    }
    @DeleteMapping("/shelf/{id}")
    public void deleteShelf(@PathVariable Long id, HttpServletRequest request) {
        userBookService.deleteShelf(id, request);
    }
    //=========================================================
    //======================책장 속 책들 ========================
    @GetMapping("/shelf/{id}")
    public Page<BookSimpleInfoResponseDto> getBookListOfShelf(@PathVariable Long id, @RequestParam int page,HttpServletRequest request) {
        return userBookService.getBookListOfShelf(id,request);
    }

}
