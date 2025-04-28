package com.app.oauth.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil {
    @Value("jwt.secret")
    private String secretKey;

//    토큰 생성 메서드
    public String generateToken(Map<String, Object> claims) {
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String email = (String) claims.get("email"); // 이메일 추출
        String name = (String) claims.get("name"); // 이름 추출

        userInfo.put("email", email);
        userInfo.put("name", name);
//    24 시간
        long exprirationTimeInMillis = 24 * 60 * 60 * 1000;
        Date exprirationDate = new Date(System.currentTimeMillis() + exprirationTimeInMillis);

        return Jwts.builder()
                .claims(userInfo)
                .setExpiration(exprirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setHeaderParam("typ", "JWT")// sha-256
                .compact();
    }

//    토큰 파싱 메서드
    public Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJwt(token)
                    .getBody(); // Claims

        } catch (ExpiredJwtException e) {
//            만료된 토큰
            log.error("Expired JWT token", e);
            throw new RuntimeException("Expired JWT token");
        } catch (Exception e) {
//            토큰 정보 일치하지 않음
            log.error("Invalid JWT token", e);
            throw new RuntimeException("Invalid JWT token");
        }
    }

//    JWT 토큰에서 이메일 추출
    public String getEmailFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("email", String.class);
    }

//    JWT 토큰에서 이름을 추출
    public String getNameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("name", String.class);
    }

//    토큰이 유효한지 아닌지 검증
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false; // 유효하지 않은 토큰
        }
    }

}
