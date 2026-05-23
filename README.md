![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-4-green)
![Spring Security](https://img.shields.io/badge/SpringSecurity-Integrated-success)
![Maven](https://img.shields.io/badge/Maven-Published-orange)
![CI](https://img.shields.io/github/actions/workflow/status/smitroy4/jwt-spring-boot-starter/maven.yml)
![License](https://img.shields.io/badge/License-MIT-yellow)

# jwt-spring-boot-starter

Production-ready JWT authentication starter for Spring Boot.

Plug in the dependency, configure JWT properties, and instantly get:

* JWT token generation
* JWT validation
* Spring Security integration
* automatic authentication filter
* protected route handling
* zero boilerplate configuration

---

# Features

## JWT Core Features

* Access token generation
* Refresh token generation
* Claims extraction
* Type-safe claim parsing
* Token validation
* Access & refresh token detection

---

## Spring Security Integration

* Automatic JWT authentication filter registration
* Automatic Spring Security configuration
* Bearer token authentication
* SecurityContext authentication support
* Route protection
* Public route exclusion support

---

## Developer Experience

* Fully auto-configured
* Minimal setup
* Java 21 compatible
* Spring Boot 4.x.x compatible
* Zero manual bean registration
* Plug-and-play architecture

---

# Installation

## 1. Add GitHub Packages Repository

```xml
<repositories>

    <repository>

        <id>github</id>

        <url>
            https://maven.pkg.github.com/smitroy4/jwt-spring-boot-starter
        </url>

    </repository>

</repositories>
```

---

## 2. Add Dependency

```xml
<dependency>

    <groupId>com.smit</groupId>

    <artifactId>
        jwt-spring-boot-starter
    </artifactId>

    <version>1.0.2</version>

</dependency>
```

---

# Configuration

## application.yml

```yaml
jwt:
  secret-key: mysupersecretkeymysupersecretkey123456
  access-token-expiration: 600000
  refresh-token-expiration: 604800000
```

---

# Quick Start

## Generate Token

```java
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @GetMapping("/auth/token")
    public String token() {

        return jwtService.generateAccessToken(
                "101",
                Map.of(
                        "email",
                        "admin@test.com",
                        "role",
                        "ADMIN"
                )
        );
    }
}
```

---

# Protected Route Example

```java
@RestController
public class UserController {

    @GetMapping("/user")
    public String user(
            Authentication authentication
    ) {

        return "Authenticated User: "
                + authentication.getName();
    }
}
```

---

# Authentication Flow

## Step 1 — Generate Token

```http
GET /auth/token
```

Response:

```text
eyJhbGciOiJIUzI1NiJ9...
```

---

## Step 2 — Access Protected Endpoint

```http
GET /user
Authorization: Bearer YOUR_TOKEN
```

Response:

```text
Authenticated User: 101
```

---

# How It Works

```text
Incoming Request
        ↓
JwtAuthenticationFilter
        ↓
Extract Bearer Token
        ↓
Validate JWT
        ↓
Extract Subject & Claims
        ↓
SecurityContext Authentication
        ↓
Protected Controller Access
```

---

# Auto Configuration Architecture

```text
application.yml
        ↓
JwtConfigurationProperties
        ↓
JwtAutoConfiguration
        ↓
JwtSecurityConfiguration
        ↓
JwtAuthenticationFilter
        ↓
JwtService
```

---

# Included Components

## Auto-Configured Beans

* JwtService
* JwtAuthenticationFilter
* SecurityFilterChain
* JwtConfigurationProperties

---

# Overriding Starter Components

The starter is fully extensible.

Applications can override:

* JwtService
* JwtAuthenticationFilter
* SecurityFilterChain

simply by defining custom beans.

Example:

```java
@Bean
public JwtService jwtService() {

    return new CustomJwtService();
}
```

The starter automatically backs off using:

```java
@ConditionalOnMissingBean
```

---

# Project Structure

```text
jwt-spring-boot-starter
│
├── config
│   ├── JwtAutoConfiguration
│   └── JwtSecurityConfiguration
│
├── properties
│   └── JwtConfigurationProperties
│
├── security
│   └── JwtAuthenticationFilter
│
└── META-INF/spring
    └── AutoConfiguration.imports
```

---

# Tech Stack

| Layer        | Technology        |
| ------------ | ----------------- |
| Language     | Java 21           |
| Framework    | Spring Boot 4.x.x |
| Security     | Spring Security   |
| JWT          | JJWT              |
| Build Tool   | Maven             |
| Distribution | GitHub Packages   |
| CI/CD        | GitHub Actions    |

---

# Current Security Features

* JWT Bearer Authentication
* Stateless Authentication
* Automatic Request Filtering
* SecurityContext Integration
* Public & Protected Route Handling

---

# Upcoming Features

* Dynamic excluded routes
* Role-based authorization
* Authorities extraction
* Redis token blacklist
* Refresh token workflow
* RSA key support
* Swagger/OpenAPI integration
* OAuth2 support
* Custom JWT annotations
* Multi-tenant JWT support

---

# Contributing

Contributions, feature ideas, and pull requests are welcome.

Feel free to open issues for:

* bug reports
* feature requests
* improvements
* integrations

---

# License

MIT License

---

# Author

## Smit Roy

GitHub:
https://github.com/smitroy4
