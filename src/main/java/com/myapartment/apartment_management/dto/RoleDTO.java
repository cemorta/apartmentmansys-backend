package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.Role;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for Role entity
 */
public class RoleDTO {
    private Long id;
    private String name;

    // User IDs and basic info that will be included if includeUsers is true
    private Set<UserDTO> users = new HashSet<>();

    public RoleDTO(Role role, boolean includeUsers) {
        this.id = role.getId();
        this.name = role.getName();

        if (includeUsers && role.getUsers() != null) {
            this.users = role.getUsers().stream()
                    .map(user -> new UserDTO(user, false))
                    .collect(Collectors.toSet());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}