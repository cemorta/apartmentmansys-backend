package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity representing occupants of a flat.
 */
@Entity
@Table(name = "flat_occupants")
public class FlatOccupant {

    @EmbeddedId
    private FlatOccupantId id;

    @ManyToOne
    @MapsId("flatId")
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @ManyToOne
    @MapsId("residentUserId")
    @JoinColumn(name = "resident_user_id")
    private ResidentProfile resident;

    @Column(name = "lease_start_date")
    private LocalDate leaseStartDate;

    @Column(name = "lease_end_date")
    private LocalDate leaseEndDate;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    // Default constructor
    public FlatOccupant() {
    }

    // Constructor with fields
    public FlatOccupant(Flat flat, ResidentProfile resident, LocalDate leaseStartDate,
                        LocalDate leaseEndDate, Boolean isPrimary) {
        this.id = new FlatOccupantId(flat.getId(), resident.getUserId());
        this.flat = flat;
        this.resident = resident;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.isPrimary = isPrimary;
    }

    // Getters and setters
    public FlatOccupantId getId() {
        return id;
    }

    public void setId(FlatOccupantId id) {
        this.id = id;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
        if (this.id == null) {
            this.id = new FlatOccupantId();
        }
        this.id.setFlatId(flat.getId());
    }

    public ResidentProfile getResident() {
        return resident;
    }

    public void setResident(ResidentProfile resident) {
        this.resident = resident;
        if (this.id == null) {
            this.id = new FlatOccupantId();
        }
        this.id.setResidentUserId(resident.getUserId());
    }

    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Override
    public String toString() {
        return "FlatOccupant{" +
                "id=" + id +
                ", leaseStartDate=" + leaseStartDate +
                ", leaseEndDate=" + leaseEndDate +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
