package com.gamza.moulle.service;

import com.gamza.moulle.dto.shelf.BookSimpleInfoResponseDto;
import com.gamza.moulle.dto.shelf.ShelfResponseDto;
import com.gamza.moulle.entity.*;
import com.gamza.moulle.jwt.JwtProvider;
import com.gamza.moulle.repository.ShelfRepository;
import com.gamza.moulle.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserBookService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final ShelfRepository shelfRepository;
    private final JPAQueryFactory jpaQueryFactory;
    //=====================책장 관련========================
    public List<ShelfResponseDto> getShelfList(HttpServletRequest request) {
        UserEntity userEntity = jwtProvider.findByUserOnToken(request);

        QShelfEntity qShelfEntity = QShelfEntity.shelfEntity;
        List<ShelfEntity> queryResult = jpaQueryFactory
                .selectFrom(qShelfEntity)
                .where(qShelfEntity.userEntity.eq(userEntity))
                .fetch();

        return queryResult.stream().map(ShelfResponseDto::new).collect(Collectors.toList());
    }
    @Transactional
    public void addShelf (String shelfName,HttpServletRequest request) {
        UserEntity userEntity = jwtProvider.findByUserOnToken(request);
        ShelfEntity shelfEntity = new ShelfEntity(userEntity, shelfName);
        shelfRepository.save(shelfEntity);
    }
    @Transactional
    public void updateShelf(Long id,String shelfName,HttpServletRequest request) {
        UserEntity userEntity = jwtProvider.findByUserOnToken(request);
        ShelfEntity shelfEntity = shelfRepository.findById(id).orElseThrow();
        if (shelfEntity.getUserEntity() != userEntity) {
            throw new RuntimeException();
        }
        shelfEntity.updateShelfName(shelfName);
    }
    @Transactional
    public void deleteShelf(Long id,HttpServletRequest request) {
        UserEntity userEntity = jwtProvider.findByUserOnToken(request);
        ShelfEntity shelfEntity = shelfRepository.findById(id).orElseThrow();
        if (shelfEntity.getUserEntity() != userEntity) {
            throw new RuntimeException();
        }
        shelfRepository.delete(shelfEntity);
    }
    //=======================================================
    //====================책장 속 책들=========================
    @Transactional
    public Page<BookSimpleInfoResponseDto> getBookListOfShelf(Long id,int page, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(page-1, 20);

        QBookEntity qBookEntity = QBookEntity.bookEntity;
        List<BookEntity> books = jpaQueryFactory
                .selectFrom(qBookEntity)
                .where(qBookEntity.shelfEntity.id.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(books.stream().map(BookSimpleInfoResponseDto::new).collect(Collectors.toList()), pageable, books.size());
    }
}
