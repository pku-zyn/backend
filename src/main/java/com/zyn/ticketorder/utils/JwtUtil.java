package com.zyn.ticketorder.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "YourSecretKeyForJWTGeneration1234567890"; // 需要长度足够长
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2小时

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 生成 JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 验证 JWT Token
    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject(); // 返回用户名
        } catch (JwtException | IllegalArgumentException e) {
            return null; // 无效 token
        }
    }
}
