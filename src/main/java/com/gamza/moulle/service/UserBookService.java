package com.gamza.moulle.service;

import com.gamza.moulle.dto.mainpage.BookshelfResponseDto;
import com.gamza.moulle.entity.BookshelfEntity;
import com.gamza.moulle.entity.UserEntity;
import com.gamza.moulle.jwt.JwtProvider;
import com.gamza.moulle.repository.BookshelfRepository;
import com.gamza.moulle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final BookshelfRepository bookshelfRepository;
    public List<BookshelfResponseDto> getBookshelfList(HttpServletRequest request) {
        String userEmail = jwtProvider.getUserEmail(jwtProvider.resolveAT(request));
        UserEntity userEntity = userRepository.findByEmail(userEmail).orElseThrow();

        List<BookshelfEntity> entityList = bookshelfRepository.findByUserEntity(userEntity);
        return entityList.stream().map(BookshelfResponseDto::new).collect(Collectors.toList());
    }
}
