package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.LoginRequest;
import com.myapartment.apartment_management.dto.UserDTO;
import com.myapartment.apartment_management.entity.User;
import com.myapartment.apartment_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByEmail(loginRequest.getEmail()));

        return optionalUser
            .filter(user -> user.getPasswordHash().equals(loginRequest.getPassword()))
            .map(user -> {
                String token = Jwts.builder()
                        .setSubject(user.getEmail())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 gün geçerli
                        .signWith(secretKey)
                        .compact();

                return ResponseEntity.ok(Map.of("token", token));
            })
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials")));



        /*if (user != null) {
            // For simplicity, we're doing a simple string comparison
            if (user.getPasswordHash().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(new UserDTO(
                        user,
                        true
                ));
            }
        }

        return ResponseEntity.badRequest().body("Invalid username or password");*/
    }
}