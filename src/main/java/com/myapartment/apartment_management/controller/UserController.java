package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.UserDTO;
import com.myapartment.apartment_management.entity.User;
import com.myapartment.apartment_management.repository.UserRepository;
import com.myapartment.apartment_management.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/staff")
    public List<UserDTO> getAllStaff() {
        return StreamSupport.stream(userService.findAllByStaffExists().spliterator(), false)
                .map(user -> new UserDTO(user, true))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> new UserDTO(user, true))
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<UserDTO>> getFilteredUsers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String profile,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<User> userPage = userService.getFilteredUsers(search, profile, role, pageable);

        // Convert Page<User> to Page<UserDTO>
        Page<UserDTO> userDtoPage = userPage.map(user -> new UserDTO(user, true));

        return ResponseEntity.ok(userDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok(new UserDTO(user, true)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(null);
        user.setCreatedAt(LocalDateTime.now()); 
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
