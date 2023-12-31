package com.rup.jwt;

import com.rup.jwt.handler.JwtAuthExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtProvider JwtProvider;


    @Override
    public void configure(HttpSecurity http){
        JwtRequestFilter jwtFilter = new JwtRequestFilter(JwtProvider);
        JwtAuthExceptionHandler jwtAuthenticationExceptionHandler = new JwtAuthExceptionHandler();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationExceptionHandler, JwtRequestFilter.class);
    }
}
