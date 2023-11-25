package com.rup.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {
    @GetMapping("/health")
    String healthCheck(){
        return "RUP Server is healthy!!";
    }
}
