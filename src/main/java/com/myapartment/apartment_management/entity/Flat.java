package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a flat within an apartment building.
 */
@Entity
@Table(name = "flats")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "flat_number", length = 10, unique = true, nullable = false)
    private String flatNumber;

    @Column(name = "num_bedrooms")
    private Integer numBedrooms;

    @Column(name = "num_bathrooms")
    private Integer numBathrooms;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    private FlatOwnerProfile owner;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Default constructor
    public Flat() {
    }

    // Constructor with fields
    public Flat(Apartment apartment, String flatNumber, Integer numBedrooms,
                Integer numBathrooms, FlatOwnerProfile owner) {
        this.apartment = apartment;
        this.flatNumber = flatNumber;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.owner = owner;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
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

    public FlatOwnerProfile getOwner() {
        return owner;
    }

    public void setOwner(FlatOwnerProfile owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", apartment=" + (apartment != null ? apartment.getId() : null) +
                ", flatNumber='" + flatNumber + '\'' +
                ", numBedrooms=" + numBedrooms +
                ", numBathrooms=" + numBathrooms +
                ", owner=" + (owner != null ? owner.getUserId() : null) +
                ", createdAt=" + createdAt +
                '}';
    }
}