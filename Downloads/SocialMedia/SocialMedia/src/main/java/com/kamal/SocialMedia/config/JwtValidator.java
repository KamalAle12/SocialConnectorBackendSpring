package com.kamal.SocialMedia.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Fetch JWT from the Authorization header
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        // Check if JWT is not null and starts with "Bearer "
        if (jwt != null && jwt.startsWith("Bearer ")) {
            // Remove "Bearer " prefix
            jwt = jwt.substring(7);

            try {
                // Validate and parse the JWT token
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(JwtConstant.SECRET_KEY.getBytes()) // Use the correct signing key
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                // Extract email from JWT
                String email = claims.get("email", String.class);

                // Authorities (populate if needed based on your app logic)
                List<GrantedAuthority> authorities = new ArrayList<>();

                // Create authentication token
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

                // Set authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (ExpiredJwtException e) {
                // Handle expired token
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is expired");
                return;
            } catch (JwtException e) {
                // Handle invalid token
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            } catch (Exception e) {
                // Catch any other exceptions
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
