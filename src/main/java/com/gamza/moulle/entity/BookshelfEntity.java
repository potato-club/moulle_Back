package com.gamza.moulle.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BookshelfEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Column(nullable = false)
    @Size(max = 20)
    private String shelfName;
    @OneToMany
    private List<UserBookEntity> bookList;

    public int getBookCount() {
        return (bookList !=null) ? bookList.size() : 0;
    }

}
