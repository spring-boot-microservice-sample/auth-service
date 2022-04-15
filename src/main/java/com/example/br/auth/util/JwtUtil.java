package com.example.br.auth.util;

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

//    @Value("$jwt.token.validity")
    private long jwtTokenValidity = 600000;

    public String generateToken(String id) {
        Claims claims = Jwts.claims().setSubject(id);
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
