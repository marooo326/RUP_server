package com.rup.jwt.handler;

import com.rup.apiPayload.code.ErrorStatus;
import com.rup.apiPayload.exception.filter.ApiErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JwtAuthExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException authException) {
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            PrintWriter writer = response.getWriter();
            String errorCodeName = authException.getMessage();
            ErrorStatus commonStatus = ErrorStatus.valueOf(errorCodeName);

            ApiErrorResult apiErrorResult = ApiErrorResult.builder()
                    .isSuccess(false)
                    .code(commonStatus.getCode())
                    .message(commonStatus.getMessage())
                    .result(null)
                    .build();

            writer.write(apiErrorResult.toString());
            writer.flush();
            writer.close();
        }
    }
}
