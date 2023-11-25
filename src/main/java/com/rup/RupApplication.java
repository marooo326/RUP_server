package com.rup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@OpenAPIDefinition(servers = {@Server(url = "/")})
@EnableJpaAuditing
@SpringBootApplication
public class RupApplication {

    public static void main(String[] args) {
        SpringApplication.run(RupApplication.class, args);
    }

}
