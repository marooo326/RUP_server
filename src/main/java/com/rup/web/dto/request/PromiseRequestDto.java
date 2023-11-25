package com.rup.web.dto.request;

import java.time.LocalDateTime;

public class PromiseRequestDto {
    public static class PromiseRegisterDto {
        private String name;
        private LocalDateTime localDateTime;
        private Long penalty;

    }
}
