package com.gamza.moulle.service;

import com.gamza.moulle.dto.LoginRequestDto;
import com.gamza.moulle.dto.SignUpRequestDto;
import com.gamza.moulle.entity.UserEntity;
import com.gamza.moulle.enums.UserRole;
import com.gamza.moulle.error.ErrorCode;
import com.gamza.moulle.error.exception.UnAuthorizedException;
import com.gamza.moulle.jwt.JwtProvider;
import com.gamza.moulle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public String localLoginService(LoginRequestDto loginRequestDto, HttpServletResponse response){
        if (!userRepository.existsByEmail(loginRequestDto.getEmail()))
            throw new UnAuthorizedException(ErrorCode.ACCESS_DENIED_EXCEPTION.getMessage(), ErrorCode.ACCESS_DENIED_EXCEPTION);

        UserEntity userEntity = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow();
        if (!passwordEncoder.matches(loginRequestDto.getPassword(),userEntity.getPassword()))
            throw new UnAuthorizedException(ErrorCode.ACCESS_DENIED_EXCEPTION.getMessage(), ErrorCode.ACCESS_DENIED_EXCEPTION);


        this.setJwtTokenHeader(loginRequestDto.getEmail(), response);

        return "200ok";
    }

    public String localSignUp(SignUpRequestDto signUpRequestDto, HttpServletResponse response) {
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new UnAuthorizedException(ErrorCode.ACCESS_DENIED_EXCEPTION.getMessage(),ErrorCode.ACCESS_DENIED_EXCEPTION);
        }
        signUpRequestDto.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        UserEntity userEntity = signUpRequestDto.toEntity();
        userRepository.save(userEntity);
        this.setJwtTokenHeader(userEntity.getEmail(), response);

        return "200ok";
    }

    private void setJwtTokenHeader(String email, HttpServletResponse response) {
        String AT = jwtProvider.createAT(email);
        String RT = jwtProvider.createRT(email);

        jwtProvider.setHeaderAT(response, AT);
        jwtProvider.setHeaderRT(response, RT);
    }
}
