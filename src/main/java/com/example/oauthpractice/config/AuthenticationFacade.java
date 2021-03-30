package com.example.oauthpractice.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationFacade {

    Authentication getAuthentication();
}
