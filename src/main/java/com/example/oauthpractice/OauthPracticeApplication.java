package com.example.oauthpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OauthPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthPracticeApplication.class, args);
    }
}
