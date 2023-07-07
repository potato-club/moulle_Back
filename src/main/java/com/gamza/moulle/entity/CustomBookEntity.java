package com.gamza.moulle.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CustomBookEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private UserEntity userEntity;

    @Column(nullable = false)
    private String bookName;

    @Column
    private String author;

    @Column(nullable = false)
    private String imageLink;

    @Column
    private String imageLink2;

    @Column
    private String description;

}
