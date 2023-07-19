package com.gamza.moulle.controller;

import com.gamza.moulle.dto.LoginRequestDto;
import com.gamza.moulle.dto.SignUpRequestDto;
import com.gamza.moulle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    String localSignUp(@RequestBody SignUpRequestDto signUpRequestDto, HttpServletResponse response) {
        return userService.localSignUp(signUpRequestDto,response);
    }
}
