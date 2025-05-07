// AuthController.java
package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.LoginRequest;
import com.myapartment.apartment_management.dto.LoginResponse;
import com.myapartment.apartment_management.security.JwtUtil;
import com.myapartment.apartment_management.entity.User;
import com.myapartment.apartment_management.dto.UserDTO;
import com.myapartment.apartment_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(userDetails);

            // Get user by email
            User user = userService.findByEmail(loginRequest.getEmail());

            // Convert to DTO
            LoginResponse loginResp = convertToDto(user);
            loginResp.setToken(jwt);

            return ResponseEntity.ok(loginResp);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("Invalid email or password"));
        }
    }

    @PostMapping("/admin-login")
    public ResponseEntity<?> adminLogin(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Get user by email
            User user = userService.findByEmail(loginRequest.getEmail());

            // Check if user is admin
            if (!user.isAdmin()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new ErrorResponse("You do not have admin privileges"));
            }

            String jwt = jwtUtil.generateToken(userDetails);

            // Convert to DTO
            LoginResponse loginResp = convertToDto(user);
            loginResp.setToken(jwt);

            return ResponseEntity.ok(loginResp);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("Invalid email or password"));
        }
    }

    private LoginResponse convertToDto(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setFirstName(user.getFirstName());
        loginResponse.setLastName(user.getLastName());
        loginResponse.setPhone(user.getPhone());
        loginResponse.setAdminProfile(user.isAdmin());
        loginResponse.setResidentProfile(user.isResident());
        loginResponse.setFlatOwnerProfile(user.isFlatOwner());
        loginResponse.setStaffProfile(user.isStaff());
        return loginResponse;
    }

    // Error response class
    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}