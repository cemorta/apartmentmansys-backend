package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.AdminProfile;

/**
 * Data Transfer Object for AdminProfile entity
 */

public class AdminProfileDTO {
    private Long userId;

    // User details that will be included if includeUserDetails is true
    private UserDTO user;

    public AdminProfileDTO(AdminProfile adminProfile) {
        if (adminProfile != null) {
            this.userId = adminProfile.getUserId();
            this.user = new UserDTO(adminProfile.getUser(), false);
        }
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
