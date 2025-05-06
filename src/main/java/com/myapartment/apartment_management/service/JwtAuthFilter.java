package com.myapartment.apartment_management.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.myapartment.apartment_management.controller.AuthController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.err.println("Token: " + token);
            // Here you would typically validate the token and set the authentication in the context
            try {
                // Validate and parse JWT
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(AuthController.secretKey) // e.g., from Keys.hmacShaKeyFor(...)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                System.err.println("Username: " + username);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Create Authentication object
                    UsernamePasswordAuthenticationToken authToken
                            = new UsernamePasswordAuthenticationToken(username, null, List.of());

                    // Set Authentication in context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (JwtException e) {
                // Invalid token — optionally log it or reject
                System.err.println("Invalid JWT token: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

