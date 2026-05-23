package com.smit.jwtstarter.config;

import com.smit.jwtstarter.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class JwtSecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    @ConditionalOnMissingBean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                // DISABLE CSRF
                .csrf(csrf -> csrf.disable())

                // AUTHORIZATION RULES
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC ROUTES
                        .requestMatchers(
                                "/auth/**"
                        ).permitAll()

                        // PROTECTED ROUTES
                        .anyRequest()
                        .authenticated()
                )

                // JWT FILTER
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                // DISABLE DEFAULT LOGIN FORM
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}