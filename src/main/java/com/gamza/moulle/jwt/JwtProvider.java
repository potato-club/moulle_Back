package com.gamza.moulle.jwt;

import com.gamza.moulle.entity.UserEntity;
import com.gamza.moulle.enums.UserRole;
import com.gamza.moulle.repository.UserRepository;
import com.gamza.moulle.service.jwt.CustomUserDetailService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Transactional
public class JwtProvider {
    private final UserRepository userRepository;
    private final CustomUserDetailService customUserDetailService;
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.accessExpiration}")
    private long ATExpireTime;

    @Value("${jwt.refreshExpiration}")
    private long RTExpireTime ;

    public String createAT(String email){
        return this.createToken(email,ATExpireTime);
    }
    public String createRT(String email){
        return this.createToken(email,RTExpireTime);
    }


    public String createToken(String email, long tokenTime){
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(()->{throw new UsernameNotFoundException("찾을 수 없음");});
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles",userEntity.getUserRole().toString());

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Date date = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+tokenTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String resolveAT(HttpServletRequest request) {
        if (request.getHeader("authorization") != null){
            return request.getHeader("authorization").substring(7);
        }
        return null;
    }
    public String resolveRT(HttpServletRequest request) {
        if (request.getHeader("refreshToken") != null){
            return request.getHeader("refreshToken").substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("JWT token has expired");
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT token is unsupported", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty", e);
        } catch (io.jsonwebtoken.security.SignatureException e) {
            throw new SignatureException("JWT signature verification failed", e);
        }
    }

    public void setHeaderAT(HttpServletResponse response, String AT) {
        response.setHeader("authorization","Bearer "+AT);
    }
    public void setHeaderRT(HttpServletResponse response, String RT) {
        response.setHeader("refreshToken","Bearer "+RT);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
    public String getUserEmail(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build();

        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }

}
