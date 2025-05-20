package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a due payment for a specific flat.
 * Links dues to flats and tracks payment status.
 */
@Entity
@Table(name = "due_payments")
public class DuePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "due_id", nullable = false)
    private Due due;

    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = false)
    private Flat flat;

    @Column(name = "is_payment_complete", nullable = false)
    private Boolean isPaymentComplete;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    // Default constructor
    public DuePayment() {
    }

    // Constructor with fields
    public DuePayment(Due due, Flat flat, Boolean isPaymentComplete) {
        this.due = due;
        this.flat = flat;
        this.isPaymentComplete = isPaymentComplete;
        this.createdAt = LocalDateTime.now();
        this.paidAt = isPaymentComplete ? LocalDateTime.now() : null;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Due getDue() {
        return due;
    }

    public void setDue(Due due) {
        this.due = due;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Boolean getIsPaymentComplete() {
        return isPaymentComplete;
    }

    public void setIsPaymentComplete(Boolean isPaymentComplete) {
        this.isPaymentComplete = isPaymentComplete;
        if (isPaymentComplete && this.paidAt == null) {
            this.paidAt = LocalDateTime.now();
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    @Override
    public String toString() {
        return "DuePayment{" +
                "id=" + id +
                ", due=" + (due != null ? due.getId() : null) +
                ", flat=" + (flat != null ? flat.getId() : null) +
                ", isPaymentComplete=" + isPaymentComplete +
                ", createdAt=" + createdAt +
                ", paidAt=" + paidAt +
                '}';
    }
}
