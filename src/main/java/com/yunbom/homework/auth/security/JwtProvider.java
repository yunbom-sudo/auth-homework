package com.yunbom.homework.auth.security;

import com.yunbom.homework.auth.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;

    public JwtProvider(
            @Value("${spring.jwt.secret}") String secretKey
    ){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }


    public String createToken(UserEntity entity){

        Claims claims = Jwts.claims();

        claims.put("email", entity.getEmail());
        claims.put("role", entity.getRole());

        Date now = new Date();

        Date expiration = new Date(
                now.getTime() + 3600000
        );

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

}