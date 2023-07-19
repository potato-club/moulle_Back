package com.gamza.moulle.jwt;

import com.gamza.moulle.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.contains("/login") || path.contains("/signup") || path.contains("/user")) {
            filterChain.doFilter(request, response);
            return;
        }

        String AT = jwtProvider.resolveAT(request);
        //만약 프론트가 accessToken에 아무것도 안넣어서 보내면 그건 refresh해달라는 의미
        if (AT == null){
            String RT = jwtProvider.resolveRT(request);
            if (jwtProvider.validateToken(RT)) {
                String userEmail = jwtProvider.getUserEmail(RT);
                AT = jwtProvider.createAT(userEmail);
                jwtProvider.setHeaderAT(response,AT);
                this.setAuthentication(AT);
            }
        } else if(AT != null) {
            if (jwtProvider.validateToken(AT)) {
                this.setAuthentication(AT);
            }
        }

        filterChain.doFilter(request,response);
    }

    private void setAuthentication(String token) {
        //토큰에서 유저정보 빼기
        Authentication authentication = jwtProvider.getAuthentication(token);
        //유저정보 뺀거 컨택스트에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
