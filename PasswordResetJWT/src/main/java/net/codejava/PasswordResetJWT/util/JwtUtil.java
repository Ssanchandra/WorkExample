package net.codejava.PasswordResetJWT.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private final SecretKey secretKey = Keys.hmacShaKeyFor("secret_key_which_should_be_long_enough_for_hmac".getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
            .signWith(secretKey, SignatureAlgorithm.HS512)
            .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean isTokenValid(String token, String email) {
        try {
            return email.equals(extractEmail(token));
        } catch (Exception e) {
            return false;
        }
    }
}
