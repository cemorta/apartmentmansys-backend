package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.FlatOccupant;
import java.time.LocalDate;

/**
 * Data Transfer Object for the FlatOccupant entity.
 */
public class FlatOccupantDTO {
    private Long flatId;
    private Long residentUserId;
    private FlatDTO flatDto;
    private UserDTO userDto;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private Boolean isPrimary;

    // Default constructor
    public FlatOccupantDTO() {}

    public FlatOccupantDTO(FlatOccupant flatOccupant, boolean includeFlat) {
        this.flatId = flatOccupant.getFlat().getId();
        this.residentUserId = flatOccupant.getResident().getUserId();
        if (includeFlat && flatOccupant.getFlat() != null) {
            this.flatDto = new FlatDTO(flatOccupant.getFlat(), false, false, false);
        }
        this.userDto = new UserDTO(flatOccupant.getResident().getUser());
        this.leaseStartDate = flatOccupant.getLeaseStartDate();
        this.leaseEndDate = flatOccupant.getLeaseEndDate();
        this.isPrimary = flatOccupant.getIsPrimary();
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public Long getResidentUserId() {
        return residentUserId;
    }

    public void setResidentUserId(Long residentUserId) {
        this.residentUserId = residentUserId;
    }

    public FlatDTO getFlatDto() {
        return flatDto;
    }

    public void setFlatDto(FlatDTO flatDto) {
        this.flatDto = flatDto;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }
}
