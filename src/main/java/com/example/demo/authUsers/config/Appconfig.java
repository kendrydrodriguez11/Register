package com.example.demo.authUsers.config;

import com.example.demo.authUsers.config.jwt.AuthorizationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
public class Appconfig {
    private final AuthenticationProvider authenticationProvider;
    private final AuthorizationToken authorizationToken;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{


        return httpSecurity
                .csrf(csrf -> {
                    csrf.disable();
                })
                .authorizeHttpRequests(auth -> {auth
                        .requestMatchers("/api/users/**").permitAll()
                        .anyRequest().authenticated();

                })
                .sessionManagement(session -> {session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);})
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authorizationToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
