package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.Flat;

public class FlatCreateDTO {
    private Long apartmentId;
    private Integer area;
    private String flatNumber;
    private Integer floorNumber;
    private Integer numBathrooms;
    private Integer numBedrooms;
    private Long ownerUserId;

    public FlatCreateDTO(Long apartmentId, Integer area, String flatNumber, Integer floorNumber, Integer numBathrooms, Integer numBedrooms, Long ownerUserId) {
        this.apartmentId = apartmentId;
        this.area = area;
        this.flatNumber = flatNumber;
        this.floorNumber = floorNumber;
        this.numBathrooms = numBathrooms;
        this.numBedrooms = numBedrooms;
        this.ownerUserId = ownerUserId;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
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

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }
}
