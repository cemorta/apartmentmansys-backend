package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.Flat;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class FlatDTO {
    private Long id;
    private ApartmentDTO apartment;
    private String flatNumber;
    private Integer floorNumber;
    private Integer area;
    private Integer numBedrooms;
    private Integer numBathrooms;
    private UserDTO owner;

    // Default constructor
    public FlatDTO() {
    }

    public FlatDTO(Flat flat, boolean includeOwner, boolean includeApartment) {
        this.id = flat.getId();
        this.flatNumber = flat.getFlatNumber();
        this.floorNumber = flat.getFloorNumber();
        this.area = flat.getArea();
        this.numBedrooms = flat.getNumBedrooms();
        this.numBathrooms = flat.getNumBathrooms();

        if (includeOwner && flat.getOwner() != null) {
            this.owner = new UserDTO(flat.getOwner().getUser());
        }
        if (includeApartment && flat.getApartment() != null) {
            this.apartment = new ApartmentDTO(flat.getApartment(), false, false);
        }
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
}
