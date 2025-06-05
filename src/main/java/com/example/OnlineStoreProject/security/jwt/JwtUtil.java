package com.example.OnlineStoreProject.security.jwt;

import com.example.OnlineStoreProject.models.enums.Role;
import com.example.OnlineStoreProject.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());

        return Jwts
                .builder()
                .claims(claims)
                .subject(user.getEmail())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey())
                .compact();
    }

    public static Claims getClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static Role getRoleFromToken(String token) {
        String role = getClaimsFromToken(token).get("role", String.class);
        return Role.valueOf(role);
    }

    public static boolean isTokenValid(String token) {
        return !isExpired(token);
    }

    private static boolean isExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("Zm9vYmFyYmF6cXV4eHl6MTIzNDU2Nzg5MDEyMzQ1Njc4OTA=");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
