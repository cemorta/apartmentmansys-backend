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

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findUserByEmail(loginRequest.getEmail());

        if (user != null) {
            // For simplicity, we're doing a simple string comparison
            if (user.getPasswordHash().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(new UserDTO(
                        user,
                        true
                ));
            }
        }

        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}