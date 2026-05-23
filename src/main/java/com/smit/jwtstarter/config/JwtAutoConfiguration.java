package com.smit.jwtstarter.config;

import com.smit.jwtcore.model.JwtProperties;
import com.smit.jwtcore.service.JwtService;
import com.smit.jwtstarter.properties.JwtConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.smit.jwtstarter.security.JwtAuthenticationFilter;

@Configuration
@EnableConfigurationProperties(
        JwtConfigurationProperties.class
)
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JwtService jwtService(
            JwtConfigurationProperties props
    ) {

        JwtProperties jwtProperties =
                JwtProperties.builder()
                        .secretKey(
                                props.getSecretKey()
                        )
                        .accessTokenExpiration(
                                props.getAccessTokenExpiration()
                        )
                        .refreshTokenExpiration(
                                props.getRefreshTokenExpiration()
                        )
                        .build();

        return new JwtService(jwtProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtService jwtService
    ) {

        return new JwtAuthenticationFilter(
                jwtService
        );
    }

}