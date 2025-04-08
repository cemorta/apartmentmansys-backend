package com.myapartment.apartment_management.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.myapartment.apartment_management.entity.ApartmentOwnership;
import com.myapartment.apartment_management.entity.ApartmentOwnershipId;

public class ApartmentOwnershipDTO {
    private ApartmentOwnershipId apartmentOwnershipId;
    private Long adminUserId;
    private Long apartmentId;
    private UserDTO adminUser;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isActive;

    // Default constructor
    public ApartmentOwnershipDTO() {
    }

    // Constructor from entity
    public ApartmentOwnershipDTO(ApartmentOwnership ownership, boolean includeApartment) {
        if (includeApartment && ownership.getApartment() != null) {
            this.apartmentId = ownership.getApartment().getId();
        }

        if (ownership.getAdminProfile() != null) {
            this.adminUser = new UserDTO(ownership.getAdminProfile().getUser(), false);
        }

        this.startDate = ownership.getStartDate();
        this.endDate = ownership.getEndDate();
        this.isActive = ownership.getIsActive();
    }

    // Getters and setters
    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public UserDTO getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(UserDTO adminUser) {
        this.adminUser = adminUser;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
