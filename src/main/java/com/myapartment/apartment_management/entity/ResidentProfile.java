package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;

/**
 * Entity representing a resident profile in the system.
 */
@Entity
@Table(name = "resident_profiles")
public class ResidentProfile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "emergency_contact", length = 100)
    private String emergencyContact;

    // Default constructor
    public ResidentProfile() {
    }

    // Constructor with fields
    public ResidentProfile(User user, String emergencyContact) {
        this.user = user;
        this.userId = user.getId();
        this.emergencyContact = emergencyContact;
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

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return "ResidentProfile{" +
                "userId=" + userId +
                ", emergencyContact='" + emergencyContact + '\'' +
                '}';
    }
}
