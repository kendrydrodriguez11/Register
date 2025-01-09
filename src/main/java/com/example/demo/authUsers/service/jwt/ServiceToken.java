package com.example.demo.authUsers.service.jwt;

import com.example.demo.authUsers.model.EntityUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Map;

public interface ServiceToken {
    String createToken(EntityUser loginUser);
    List<SimpleGrantedAuthority> detailsClaims(String token);
    String nameUser(String token);
    Boolean validateToken(String token);
}
