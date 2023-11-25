package com.rup.jwt;

import com.rup.apiPayload.code.ErrorStatus;
import com.rup.apiPayload.exception.handler.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = request;
        String jwt = jwtProvider.resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)){
            Authentication authentication = jwtProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else{
            try {
                throw new JwtAuthenticationException(ErrorStatus.INVALID_TOKEN_EXCEPTION);
            } catch (JwtAuthenticationException e) {
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(httpServletRequest, response);
    }
}
