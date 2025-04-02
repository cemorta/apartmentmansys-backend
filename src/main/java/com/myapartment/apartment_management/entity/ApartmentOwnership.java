package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing the ownership/management relationship between AdminProfiles and Apartments.
 * Implements a many-to-many relationship with additional attributes.
 */
@Entity
@Table(name = "apartment_ownerships")
public class ApartmentOwnership {

    @EmbeddedId
    private ApartmentOwnershipId id;

    @ManyToOne
    @MapsId("adminUserId")
    @JoinColumn(name = "admin_user_id", nullable = false)
    private AdminProfile adminProfile;

    @ManyToOne
    @MapsId("apartmentId")
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    // Default constructor
    public ApartmentOwnership() {
    }

    // Constructor with fields
    public ApartmentOwnership(AdminProfile adminProfile, Apartment apartment,
                              LocalDateTime startDate) {
        this.id = new ApartmentOwnershipId(apartment.getId(), adminProfile.getUserId());
        this.adminProfile = adminProfile;
        this.apartment = apartment;
        this.startDate = startDate;
        this.isActive = true;
    }

    // Getters and setters
    public ApartmentOwnershipId getId() {
        return id;
    }

    public void setId(ApartmentOwnershipId id) {
        this.id = id;
    }

    public AdminProfile getAdminProfile() {
        return adminProfile;
    }

    public void setAdminProfile(AdminProfile adminProfile) {
        this.adminProfile = adminProfile;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ApartmentOwnership{" +
                ", adminProfile=" + adminProfile.getUserId() +
                ", apartment=" + apartment.getId() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
