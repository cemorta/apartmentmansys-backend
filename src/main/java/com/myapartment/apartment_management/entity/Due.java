package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a due payment for an apartment.
 */
@Entity
@Table(name = "dues")
public class Due {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "period", length = 6, nullable = false)
    private String period; // Format: YYYYMM

    @OneToMany(mappedBy = "due", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DuePayment> duePayments = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Default constructor
    public Due() {
    }

    // Constructor with fields
    public Due(Apartment apartment, BigDecimal cost, String period) {
        this.apartment = apartment;
        this.cost = cost;
        this.period = period;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Due{" +
                "id=" + id +
                ", apartment=" + (apartment != null ? apartment.getId() : null) +
                ", cost=" + cost +
                ", period='" + period + '\'' +
                ", duePayments=" + duePayments.size() +
                ", createdAt=" + createdAt +
                '}';
    }

    public Set<DuePayment> getDuePayments() {
        return duePayments;
    }

    public void setDuePayments(Set<DuePayment> duePayments) {
        this.duePayments = duePayments;
    }

    // Helper methods to manage the bidirectional relationship
    public void addDuePayment(DuePayment duePayment) {
        duePayments.add(duePayment);
        duePayment.setDue(this);
    }

    public void removeDuePayment(DuePayment duePayment) {
        duePayments.remove(duePayment);
        duePayment.setDue(null);
    }
}
