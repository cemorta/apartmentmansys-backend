package com.myapartment.apartment_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key for ApartmentOwnership entity.
 */
@Embeddable
public class ApartmentOwnershipId implements Serializable {

    @Column(name = "admin_user_id")
    private Long adminUserId;

    @Column(name = "apartment_id")
    private Long apartmentId;

    // Default constructor
    public ApartmentOwnershipId() {
    }

    // Constructor with fields
    public ApartmentOwnershipId(Long adminUserId, Long apartmentId) {
        this.adminUserId = adminUserId;
        this.apartmentId = apartmentId;
    }

    // Getters and setters
    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Long getOwnerUserId() {
        return adminUserId;
    }

    public void setOwnerUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentOwnershipId that = (ApartmentOwnershipId) o;
        return Objects.equals(apartmentId, that.apartmentId) &&
                Objects.equals(adminUserId, that.adminUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentId, adminUserId);
    }

    @Override
    public String toString() {
        return "ApartmentOwnershipId{" +
                "apartmentId=" + apartmentId +
                ", adminUserId=" + adminUserId +
                '}';
    }
}
