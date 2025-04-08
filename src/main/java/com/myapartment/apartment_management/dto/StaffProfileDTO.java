package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.StaffProfile;

/**
 * Data Transfer Object for StaffProfile entity
 */

public class StaffProfileDTO {
    private Long userId;

    // User details that will be included if includeUserDetails is true
    private UserDTO user;

    public StaffProfileDTO(StaffProfile staffProfile) {
        if (staffProfile != null) {
            this.userId = staffProfile.getUserId();
            this.user = new UserDTO(staffProfile.getUser(), false);
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
