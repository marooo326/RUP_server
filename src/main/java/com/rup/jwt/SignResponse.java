package com.rup.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class SignResponse {
    private String token;
}
