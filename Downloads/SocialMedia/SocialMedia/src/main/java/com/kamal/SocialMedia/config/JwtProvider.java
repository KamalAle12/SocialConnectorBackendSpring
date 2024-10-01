package com.kamal.SocialMedia.config;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    // Secret key for signing the JWT
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    /**
     * Generates a JWT token for the given authentication.
     *
     * @param auth The authentication object containing user details.
     * @return The generated JWT token.
     */
    public static String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuer("Kamal") // Issuer of the token
                .setIssuedAt(new Date()) // Token issuance time
                .setExpiration(new Date(new Date().getTime() + 86400000)) // Token expiration time (24 hours)
                .claim("email", auth.getName()) // Custom claim with the user's email
                .signWith(key) // Sign the token with the secret key
                .compact(); // Create the JWT token
    }

    /**
     * Extracts the email from the JWT token.
     *
     * @param jwt The JWT token from which to extract the email.
     * @return The email extracted from the JWT token.
     * @throws RuntimeException if the token is invalid or expired.
     */
    public static String getEmailFromJwtToken(String jwt) {
        try {
            // Check and remove Bearer prefix if present
            if (jwt != null && jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            } else {
                throw new RuntimeException("JWT Token does not begin with Bearer String");
            }

            // Parse the JWT token and extract claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key) // Set the key used to sign the token
                    .build()
                    .parseClaimsJws(jwt) // Parse the JWT token
                    .getBody();

            // Extract and return the email claim
            return String.valueOf(claims.get("email"));
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("JWT token is expired", e); // Handle expired token
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token", e); // Handle invalid token
        }
    }
}
