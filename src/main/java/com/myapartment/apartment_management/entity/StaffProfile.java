package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity representing a staff profile in the system.
 */
@Entity
@Table(name = "staff_profiles")
public class StaffProfile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    // Default constructor
    public StaffProfile() {
    }

    // Constructor with fields
    public StaffProfile(User user, String specialization, LocalDate hireDate) {
        this.user = user;
        this.userId = user.getId();
        this.specialization = specialization;
        this.hireDate = hireDate;
    }

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "StaffProfile{" +
                "userId=" + userId +
                ", specialization='" + specialization + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
