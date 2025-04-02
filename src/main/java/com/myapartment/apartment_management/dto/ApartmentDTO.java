package com.myapartment.apartment_management.dto;

import java.util.Set;
import java.util.stream.Collectors;
import com.myapartment.apartment_management.entity.Apartment;

public class ApartmentDTO {
    private Long id;
    private String buildingName;
    private String unitNumber;
    private Integer floor;
    private Set<ApartmentOwnershipDTO> ownerships;

    // Default constructor
    public ApartmentDTO() {
    }

    // Constructor from entity
    public ApartmentDTO(Apartment apartment, boolean includeOwnerships) {
        this.id = apartment.getId();
        this.buildingName = apartment.getBuildingName();
        this.unitNumber = apartment.getUnitNumber();
        this.floor = apartment.getFloor();

        if (includeOwnerships && apartment.getApartmentOwnerships() != null) {
            this.ownerships = apartment.getApartmentOwnerships().stream()
                    .map(ownership -> new ApartmentOwnershipDTO(ownership, false))
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Set<ApartmentOwnershipDTO> getOwnerships() {
        return ownerships;
    }

    public void setOwnerships(Set<ApartmentOwnershipDTO> ownerships) {
        this.ownerships = ownerships;
    }
}