package com.pdp.springm10.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * @author Aliabbos Ashurov
 * @since 14/September/2024  14:10
 **/
@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "nSeekdNjjy5XETrhlZwTNdLtzw+gn6JnDhIhUCCXm54=";
    private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24;
    private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    public String generateAccessToken(@NonNull String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(@NonNull String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private static Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public static String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
