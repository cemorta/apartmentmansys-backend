package com.myapartment.apartment_management.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import com.myapartment.apartment_management.entity.*;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private Set<String> roles;
    private ResidentProfileDTO residentProfile;
    private FlatOwnerProfileDTO flatOwnerProfile;
    private AdminProfileDTO adminProfile;
    private StaffProfileDTO staffProfile;

    // Default constructor
    public UserDTO() {
    }

    // Constructor from entity
    public UserDTO(User user, boolean getProfileInfo) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.createdAt = user.getCreatedAt();

        if(getProfileInfo) {
            if (user.getResidentProfile() != null) {
                this.residentProfile = new ResidentProfileDTO(user.getResidentProfile());
            }
            if (user.getFlatOwnerProfile() != null) {
                this.flatOwnerProfile = new FlatOwnerProfileDTO(user.getFlatOwnerProfile());
            }
            if (user.getAdminProfile() != null) {
                this.adminProfile = new AdminProfileDTO(user.getAdminProfile());
            }
            if (user.getStaffProfile() != null) {
                this.staffProfile = new StaffProfileDTO(user.getStaffProfile());
            }
        }

        // Assuming there's a getRoles method that returns a Set<Role>
        if (user.getRoles() != null) {
            this.roles = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public ResidentProfileDTO getResidentProfile() {
        return residentProfile;
    }

    public void setResidentProfile(ResidentProfileDTO residentProfile) {
        this.residentProfile = residentProfile;
    }

    public FlatOwnerProfileDTO getFlatOwnerProfile() {
        return flatOwnerProfile;
    }

    public void setFlatOwnerProfile(FlatOwnerProfileDTO flatOwnerProfile) {
        this.flatOwnerProfile = flatOwnerProfile;
    }

    public AdminProfileDTO getAdminProfile() {
        return adminProfile;
    }

    public void setAdminProfile(AdminProfileDTO adminProfile) {
        this.adminProfile = adminProfile;
    }

    public StaffProfileDTO getStaffProfile() {
        return staffProfile;
    }

    public void setStaffProfile(StaffProfileDTO staffProfile) {
        this.staffProfile = staffProfile;
    }
}
