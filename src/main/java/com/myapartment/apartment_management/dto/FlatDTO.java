package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.Flat;
import com.myapartment.apartment_management.entity.FlatOccupant;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class FlatDTO {
    private Long id;
    private ApartmentDTO apartment;
    private Long apartmentId;
    private Long ownerUserId;
    private String flatNumber;
    private Integer floorNumber;
    private Integer area;
    private Integer numBedrooms;
    private Integer numBathrooms;
    private UserDTO owner;
    private Set<FlatOccupantDTO> flatOccupants;

    // Default constructor
    public FlatDTO() {
    }

    public FlatDTO(Flat flat, boolean includeOwner, boolean includeApartment, boolean includeFlatOccupant) {
        this.id = flat.getId();
        this.flatNumber = flat.getFlatNumber();
        this.floorNumber = flat.getFloorNumber();
        this.area = flat.getArea();
        this.numBedrooms = flat.getNumBedrooms();
        this.numBathrooms = flat.getNumBathrooms();

        if (includeOwner && flat.getOwner() != null) {
            this.owner = new UserDTO(flat.getOwner().getUser(), false);
        }
        if (includeApartment && flat.getApartment() != null) {
            this.apartment = new ApartmentDTO(flat.getApartment(), false, false);
        }
        if (includeFlatOccupant && flat.getFlatOccupants() != null) {
            this.flatOccupants = flat.getFlatOccupants().stream()
                    .map(flatOccupant -> new FlatOccupantDTO(flatOccupant, false))
                    .collect(Collectors.toSet());
        }
    }

    public ApartmentDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentDTO apartment) {
        this.apartment = apartment;
    }

    public Set<FlatOccupantDTO> getFlatOccupants() {
        return flatOccupants;
    }

    public void setFlatOccupants(Set<FlatOccupantDTO> flatOccupants) {
        this.flatOccupants = flatOccupants;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(Integer numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public Integer getNumBathrooms() {
        return numBathrooms;
    }

    public void setNumBathrooms(Integer numBathrooms) {
        this.numBathrooms = numBathrooms;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
