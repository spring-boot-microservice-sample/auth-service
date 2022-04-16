package com.example.br.auth.util;

import com.example.br.auth.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    private final long jwtTokenValidity = 30 * 60 * 1000;

    public String generateToken(Long user_id) {
        Claims claims = Jwts.claims().setSubject(user_id.toString());
        long nowMillis = System.currentTimeMillis();
        long expiryMillis = nowMillis + jwtTokenValidity;
        Date expiry = new Date(expiryMillis);

        return  Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }
}
