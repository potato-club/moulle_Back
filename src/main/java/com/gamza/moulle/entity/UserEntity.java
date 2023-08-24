package com.gamza.moulle.entity;

import com.gamza.moulle.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEntity extends BaseTimeEntity{

    @Id
    private String uid;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserBookEntity> userBookList;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

}
