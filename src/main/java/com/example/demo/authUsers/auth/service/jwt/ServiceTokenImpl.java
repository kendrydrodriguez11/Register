package com.example.demo.authUsers.auth.service.jwt;

import com.example.demo.authUsers.auth.model.EntityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class ServiceTokenImpl implements ServiceToken {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String expiration;


    public String createToken(EntityUser user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(getSignature(), SignatureAlgorithm.HS256)
                .compact();

    }


    private Key getSignature(){
        byte[] signature = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(signature);
    }


    public List<SimpleGrantedAuthority> detailsClaims(String token){
        List<Map<String, String>> getRolesClaims = getClaimsName(token, claims -> claims.get("roles", List.class));
        System.out.println(getRolesClaims);
        List<SimpleGrantedAuthority> roles = getRolesClaims.stream()
                .map(role -> new SimpleGrantedAuthority(role.get("authority")))
                .collect(Collectors.toList());
        System.out.println(roles);
        return roles;
    }

    public String nameUser(String token){
        return getClaimsName(token, Claims::getSubject);
    }


    public <T> T getClaimsName(String token, Function<Claims, T> claimsFuncition){
        Claims claims = ClaimsOK(token);
        return  claimsFuncition.apply(claims);
    }



    private  Claims ClaimsOK(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSignature())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e){
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }

    public Boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignature())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return  true;
        }catch (Exception e){
            return false;
        }

    }

}
