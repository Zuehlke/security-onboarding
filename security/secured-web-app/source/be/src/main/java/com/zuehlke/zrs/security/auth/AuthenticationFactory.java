package com.zuehlke.zrs.security.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Created by nesp on 13-Oct-16.
 */
@Configuration
public class AuthenticationFactory {

    @Bean
    @RequestScope(proxyMode = ScopedProxyMode.INTERFACES)
    Authentication createAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
