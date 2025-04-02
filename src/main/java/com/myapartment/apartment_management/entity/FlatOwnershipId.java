package com.myapartment.apartment_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key for FlatOwnership entity.
 */
@Embeddable
public class FlatOwnershipId implements Serializable {

    @Column(name = "flat_id")
    private Long flatId;

    @Column(name = "owner_user_id")
    private Long ownerUserId;

    // Default constructor
    public FlatOwnershipId() {
    }

    // Constructor with fields
    public FlatOwnershipId(Long flatId, Long ownerUserId) {
        this.flatId = flatId;
        this.ownerUserId = ownerUserId;
    }

    // Getters and setters
    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatOwnershipId that = (FlatOwnershipId) o;
        return Objects.equals(flatId, that.flatId) &&
                Objects.equals(ownerUserId, that.ownerUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatId, ownerUserId);
    }

    @Override
    public String toString() {
        return "FlatOwnershipId{" +
                "flatId=" + flatId +
                ", ownerUserId=" + ownerUserId +
                '}';
    }
}
