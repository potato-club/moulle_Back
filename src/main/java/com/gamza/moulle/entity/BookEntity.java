package com.gamza.moulle.entity;

import com.gamza.moulle.enums.ISBNType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "search_result")
public class BookEntity extends BaseTimeEntity{ // #뒤에 붙은게 알라딘api에서 제공하는 데이터와 맵핑될 이름

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookName; //#title

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private ISBNType isbnType;  //ISBN10, 13 -> api에 들어갈 string

    @Column(nullable = false)
    private int isbn; //isbn 코드

    @Column(nullable = false)
    private String author; //저자 #author

    @Column
    private LocalDateTime publishedDate; // 출판일 #pubDate

    @Column
    private int price; //정가 #priceStandard

    @Column(nullable = false)
    private String imageLink; //#cover

    @Column
    private String imageLink2; //0705 회의 추가

    @Column
    private String description; // 도서 소개 #description

    @Column
    private String bookIndex; // 책 목차 #toc

    @Column
    private String bookDetailLink; //세부 링크 #link

    @Column()
    private String seriesId; // 시리즈일때 아이디

}
