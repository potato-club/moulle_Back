package com.gamza.moulle.entity;

import com.gamza.moulle.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class UserEntity extends BaseTimeEntity{
    @Id
    private String uid;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<UserBookEntity> userBooks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;
}
