package com.example.hospitalbloodbank.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManager {

    //设置token的私钥
    @Value("{token.securityKey}")
    private String securityKey;

    //过期时间为7天
    private long exp = 60 * 60 * 24 * 7 * 1000;

    /**
     * 使用jwt生成token
     *
     * @param username
     * @return
     */
    public String crtToken(String username) {
        String token = Jwts.builder().setSubject(username)
                //设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(SignatureAlgorithm.HS256, securityKey).compact();
        return token;
    }

    /**
     * 用jwt解析token获得用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }


}
