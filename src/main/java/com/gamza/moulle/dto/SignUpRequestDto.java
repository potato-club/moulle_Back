package com.gamza.moulle.dto;

import com.gamza.moulle.entity.UserEntity;
import com.gamza.moulle.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class SignUpRequestDto {
    private String email;
    private String password;
    private String nickname;

    public UserEntity toEntity(){
        UserEntity userEntity = UserEntity.builder()
                .uid(String.valueOf(UUID.randomUUID()))
                .email(email)
                .password(password)
                .userRole(UserRole.USER)
                .nickname(nickname)
                .build();
        return userEntity;
    }
}
