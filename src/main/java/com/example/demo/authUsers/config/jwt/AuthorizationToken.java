package com.example.demo.authUsers.config.jwt;

import com.example.demo.authUsers.user.service.jwt.ServiceToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collection;


@RequiredArgsConstructor
@Configuration
public class AuthorizationToken extends OncePerRequestFilter {
    private final ServiceToken serviceToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromCookie(request);

        if(serviceToken.validateToken(token)){
            Collection<? extends GrantedAuthority> roles = serviceToken.detailsClaims(token);
          String nombre = serviceToken.nameUser(token);
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(nombre, null, roles);
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }else {
            System.out.println("token invalid");
        }
        filterChain.doFilter(request, response);
    }


    private String getTokenFromCookie(HttpServletRequest request) {
        try {
            String header = request.getHeader("Authorization");
            if(header != null && header.startsWith("Bearer ")){
                return header.substring(7);
            }
        } catch (Exception e) {
            System.out.println("Error en obtener el token desde las cookies: " + e.getMessage());
        }
        return null;
    }

}
