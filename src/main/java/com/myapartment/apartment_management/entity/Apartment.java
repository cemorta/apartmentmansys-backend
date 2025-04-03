package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

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

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ApartmentOwnership> apartmentOwnerships = new HashSet<>();

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Flat> apartmentFlats = new HashSet<>();

    // Default constructor
    public Apartment() {
    }

    // TODO: Make all variables optional
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

    public void addApartmentOwnership(ApartmentOwnership ownership) {
        apartmentOwnerships.add(ownership);
        ownership.setApartment(this);
    }

    // Method to remove apartment ownership
    public void removeApartmentOwnership(ApartmentOwnership ownership) {
        apartmentOwnerships.remove(ownership);
        ownership.setApartment(null);
    }

    // Getter and setter for the collection
    public Set<ApartmentOwnership> getApartmentOwnerships() {
        return apartmentOwnerships;
    }

    public void setApartmentOwnerships(Set<ApartmentOwnership> apartmentOwnerships) {
        this.apartmentOwnerships = apartmentOwnerships;
    }

    public void addApartmentFlat(Flat flat) {
        apartmentFlats.add(flat);
        flat.setApartment(this);
    }

    // Method to remove apartment ownership
    public void removeApartmentFlat(Flat flat) {
        apartmentFlats.remove(flat);
        flat.setApartment(null);
    }

    // Getter and setter for the collection
    public Set<Flat> getApartmentFlats() {
        return apartmentFlats;
    }

    public void setApartmentFlats(Set<Flat> apartmentFlats) {
        this.apartmentFlats = apartmentFlats;
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