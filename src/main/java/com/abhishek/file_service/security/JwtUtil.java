package com.abhishek.file_service.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil{
    private final Key key =Keys.hmacShaKeyFor("mysecretforthisprojectspringsecurity123".getBytes());
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            System.out.println("Authentication success");
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Authentication failed");
            return false;
        }
    }
}
