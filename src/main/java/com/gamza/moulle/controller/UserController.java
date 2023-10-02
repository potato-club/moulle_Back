package com.gamza.moulle.controller;

import com.gamza.moulle.dto.shelf.AchieceResponseDto;
import com.gamza.moulle.dto.login.LoginRequestDto;
import com.gamza.moulle.dto.login.SignUpRequestDto;
import com.gamza.moulle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String localLogin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.localLoginService(loginRequestDto,response);
    }

    @PostMapping ("/signup")
    String localSignUp(@ModelAttribute SignUpRequestDto signUpRequestDto, HttpServletResponse response) {
        return userService.localSignUp(signUpRequestDto,response);
    }
    @GetMapping("/achieve")
    List<AchieceResponseDto> myAchieve(HttpServletRequest request) {
        return null;
    }
}
