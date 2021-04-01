package com.example.oauthpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PortFolio2Application {

    public static void main(String[] args) {
        SpringApplication.run(PortFolio2Application.class, args);
    }
}
