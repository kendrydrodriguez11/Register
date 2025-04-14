package com.example.demo.authUsers.user.service.jwt;

import com.example.demo.authUsers.user.model.EntityUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface ServiceToken {
    String createToken(EntityUser loginUser);
    List<SimpleGrantedAuthority> detailsClaims(String token);
    String nameUser(String token);
    Boolean validateToken(String token);
}
