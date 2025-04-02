package com.myapartment.apartment_management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite primary key for FlatOccupant entity.
 */
@Embeddable
public class FlatOccupantId implements Serializable {

    @Column(name = "flat_id")
    private Long flatId;

    @Column(name = "resident_user_id")
    private Long residentUserId;

    // Default constructor
    public FlatOccupantId() {
    }

    // Constructor with fields
    public FlatOccupantId(Long flatId, Long residentUserId) {
        this.flatId = flatId;
        this.residentUserId = residentUserId;
    }

    // Getters and setters
    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public Long getResidentUserId() {
        return residentUserId;
    }

    public void setResidentUserId(Long residentUserId) {
        this.residentUserId = residentUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatOccupantId that = (FlatOccupantId) o;
        return Objects.equals(flatId, that.flatId) &&
                Objects.equals(residentUserId, that.residentUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatId, residentUserId);
    }

    @Override
    public String toString() {
        return "FlatOccupantId{" +
                "flatId=" + flatId +
                ", residentUserId=" + residentUserId +
                '}';
    }
}
