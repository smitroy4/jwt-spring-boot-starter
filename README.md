# jwt-spring-boot-starter

Auto-configured JWT support for Spring Boot 4 — plug in the dependency, configure three properties, and get a production-ready `JwtService` with zero boilerplate.

---

## Features

- Access token & refresh token generation
- Claims extraction with type safety
- Token validation
- Full Spring Boot auto-configuration via `application.yml`
- Java 21 + Spring Boot 4.x.x compatible
- Lightweight — no extra beans, no manual wiring

---

## Installation

### 1. Add the GitHub Packages repository

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/smitroy4/jwt-spring-boot-starter</url>
    </repository>
</repositories>
```

### 2. Add the dependency

```xml
<dependency>
    <groupId>com.smit</groupId>
    <artifactId>jwt-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## Configuration

```yaml
jwt:
  secret-key: mysupersecretkeymysupersecretkey123456
  access-token-expiration: 600000      # 10 minutes (ms)
  refresh-token-expiration: 604800000  # 7 days (ms)
```

---

## Usage

Inject `JwtService` — it's auto-registered, no `@Bean` needed.

```java
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    // Generate access token with custom claims
    @PostMapping("/auth/token")
    public String generateToken() {
        return jwtService.generateAccessToken(
            "101",
            Map.of("email", "user@example.com", "role", "USER")
        );
    }

    // Generate refresh token
    @PostMapping("/auth/refresh")
    public String generateRefreshToken() {
        return jwtService.generateRefreshToken("101");
    }

    // Validate a token
    @GetMapping("/auth/validate")
    public boolean validate(@RequestParam String token) {
        return jwtService.validateToken(token);
    }

    // Extract subject (user ID)
    @GetMapping("/auth/subject")
    public String subject(@RequestParam String token) {
        return jwtService.extractSubject(token);
    }

    // Extract a typed claim
    @GetMapping("/auth/claim")
    public String email(@RequestParam String token) {
        return jwtService.extractClaim(token, "email", String.class);
    }
}
```

---

## How Auto-Configuration Works

```
application.yml
      ↓
JwtConfigurationProperties   (binds jwt.* properties)
      ↓
JwtAutoConfiguration         (registers JwtService bean)
      ↓
JwtService                   (available in your application context)
```

Registered via `META-INF/spring/AutoConfiguration.imports` — standard Spring Boot SPI, no component scanning required.

---

## Project Structure

```
jwt-spring-boot-starter
├── config
│   └── JwtAutoConfiguration.java
├── properties
│   └── JwtConfigurationProperties.java
└── META-INF/spring
    └── AutoConfiguration.imports
```

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.x.x |
| JWT Library | JJWT |
| Build | Maven |
| Distribution | GitHub Packages |

---

<!--## Roadmap

- [ ] JWT authentication filter
- [ ] Spring Security integration
- [ ] Role-based route authorization
- [ ] Route exclusion config
- [ ] Token blacklisting
- [ ] Redis support
- [ ] RSA key support
- [ ] OAuth2 compatibility
- [ ] Custom JWT annotations (`@JwtProtected`, etc.)
- [ ] Rate limiting integration
-->
---

## Contributing

PRs and feature suggestions are welcome. Open an issue to discuss before submitting a large change.

---

## License

MIT

---

## Author

**Smit Roy** — [github.com/smitroy4](https://github.com/smitroy4)
