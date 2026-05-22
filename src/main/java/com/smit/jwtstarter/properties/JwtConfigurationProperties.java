package com.smit.jwtstarter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigurationProperties {

    private String secretKey;

    private Long accessTokenExpiration =
            1000L * 60 * 10;

    private Long refreshTokenExpiration =
            1000L * 60 * 60 * 24 * 7;
}