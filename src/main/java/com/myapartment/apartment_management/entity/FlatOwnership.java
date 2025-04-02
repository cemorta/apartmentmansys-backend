package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity representing the ownership of a flat.
 */
@Entity
@Table(name = "flat_ownerships")
public class FlatOwnership {

    @EmbeddedId
    private FlatOwnershipId id;

    @ManyToOne
    @MapsId("flatId")
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @ManyToOne
    @MapsId("ownerUserId")
    @JoinColumn(name = "owner_user_id")
    private FlatOwnerProfile owner;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "ownership_percentage", precision = 5, scale = 2)
    private BigDecimal ownershipPercentage;

    // Default constructor
    public FlatOwnership() {
    }

    // Constructor with fields
    public FlatOwnership(Flat flat, FlatOwnerProfile owner, LocalDate purchaseDate, BigDecimal ownershipPercentage) {
        this.id = new FlatOwnershipId(flat.getId(), owner.getUserId());
        this.flat = flat;
        this.owner = owner;
        this.purchaseDate = purchaseDate;
        this.ownershipPercentage = ownershipPercentage;
    }

    // Getters and setters
    public FlatOwnershipId getId() {
        return id;
    }

    public void setId(FlatOwnershipId id) {
        this.id = id;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
        if (this.id == null) {
            this.id = new FlatOwnershipId();
        }
        this.id.setFlatId(flat.getId());
    }

    public FlatOwnerProfile getOwner() {
        return owner;
    }

    public void setOwner(FlatOwnerProfile owner) {
        this.owner = owner;
        if (this.id == null) {
            this.id = new FlatOwnershipId();
        }
        this.id.setOwnerUserId(owner.getUserId());
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getOwnershipPercentage() {
        return ownershipPercentage;
    }

    public void setOwnershipPercentage(BigDecimal ownershipPercentage) {
        this.ownershipPercentage = ownershipPercentage;
    }

    @Override
    public String toString() {
        return "FlatOwnership{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", ownershipPercentage=" + ownershipPercentage +
                '}';
    }
}
