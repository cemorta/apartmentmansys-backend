package com.myapartment.apartment_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity representing an apartment in the system.
 */
@Entity
@Table(name = "apartments")
public class Apartment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "building_name", length = 50)
    private String buildingName;
    
    @Column(name = "unit_number", length = 10, unique = true, nullable = false)
    private String unitNumber;
    
    @Column(name = "floor")
    private Integer floor;
    
    // Default constructor
    public Apartment() {
    }
    
    // Constructor with fields
    public Apartment(String buildingName, String unitNumber, Integer floor) {
        this.buildingName = buildingName;
        this.unitNumber = unitNumber;
        this.floor = floor;
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
    
    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", buildingName='" + buildingName + '\'' +
                ", unitNumber='" + unitNumber + '\'' +
                ", floor=" + floor +
                '}';
    }
}