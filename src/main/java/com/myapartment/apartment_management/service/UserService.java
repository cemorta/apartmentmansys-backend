package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.dto.UserDTO;
import com.myapartment.apartment_management.entity.Role;
import com.myapartment.apartment_management.entity.User;
import com.myapartment.apartment_management.repository.RoleRepository;
import com.myapartment.apartment_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    public Page<User> getAllUsers(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }

    public Page<User> getFilteredUsers(String search, String profile, String role, Pageable pageable) {
        Specification<User> spec = Specification.where(null);

        // Add search filter (for name or email)
        if (search != null && !search.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + search.toLowerCase() + "%"),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + search.toLowerCase() + "%"),
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + search.toLowerCase() + "%")
                    )
            );
        }

        // Add profile filter
        if (profile != null && !profile.isEmpty()) {
            String profileField = mapProfileToField(profile);
            if (profileField != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.isNotNull(root.get(profileField))
                );
            }
        }

        // Add role filter
        if (role != null && !role.isEmpty()) {
            Role roleEntity = mapRoleToField(role);
            if (roleEntity != null) {
                final Role finalRoleEntity = roleEntity; // Required for lambda
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.isMember(finalRoleEntity, root.get("roles"))
                );
            }
        }

        return userRepository.findAll(spec, pageable);
    }

    private String mapProfileToField(String profile) {
        return switch (profile) {
            case "Flat Owner" -> "flatOwnerProfile";
            case "Admin" -> "adminProfile";
            case "Resident" -> "residentProfile";
            case "Staff" -> "staffProfile";
            default -> null;
        };
    }

    private Role mapRoleToField(String roleName) {
        return switch (roleName) {
            case "Apartment Manager" -> roleRepository.findByName("apartment_manager");
            case "Super Admin" -> roleRepository.findByName("super_admin");
            default -> null;
        };
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Add other service methods as needed
}
