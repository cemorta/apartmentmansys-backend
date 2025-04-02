package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

/**
 * Entity representing a flat owner profile in the system.
 */
@Entity
@Table(name = "flat_owner_profiles")
public class FlatOwnerProfile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    // Default constructor
    public FlatOwnerProfile() {
    }

    // Constructor with fields
    public FlatOwnerProfile(User user, String paymentDetails) {
        this.user = user;
        this.userId = user.getId();
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

    @Override
    public String toString() {
        return "FlatOwnerProfile{" +
                "userId=" + userId +
                '}';
    }
}
