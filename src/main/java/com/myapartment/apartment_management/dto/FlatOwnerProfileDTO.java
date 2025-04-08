package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.FlatOwnerProfile;

/**
 * Data Transfer Object for FlatOwnerProfile entity
 */

public class FlatOwnerProfileDTO {
    private Long userId;

    // User details that will be included if includeUserDetails is true
    private UserDTO user;

    public FlatOwnerProfileDTO(FlatOwnerProfile flatOwnerProfile) {
        if (flatOwnerProfile != null) {
            this.userId = flatOwnerProfile.getUserId();
            this.user = new UserDTO(flatOwnerProfile.getUser(), false);
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
