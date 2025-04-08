package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.ResidentProfile;

/**
 * Data Transfer Object for ResidentProfile entity
 */

public class ResidentProfileDTO {
    private Long userId;

    // User details that will be included if includeUserDetails is true
    private UserDTO user;

    public ResidentProfileDTO(ResidentProfile residentProfile) {
        if (residentProfile != null) {
            this.userId = residentProfile.getUserId();
            this.user = new UserDTO(residentProfile.getUser(), false);
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
