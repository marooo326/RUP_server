package com.rup.apiPayload.exception.handler;

import com.rup.apiPayload.code.ErrorStatus;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(ErrorStatus code){
        super(code.name());
    }
}
