package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * Entity representing an admin profile in the system.
 */
@Entity
@Table(name = "admin_profiles")
public class AdminProfile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role_level", length = 20)
    private String roleLevel;

    // AdminProfile.java - Add this field and related methods
    @OneToMany(mappedBy = "adminProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ApartmentOwnership> apartmentOwnerships = new HashSet<>();

    // Default constructor
    public AdminProfile() {
    }

    // Constructor with fields
    public AdminProfile(User user, String roleLevel) {
        this.user = user;
        this.userId = user.getId();
        this.roleLevel = roleLevel;
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

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public void addApartmentOwnership(ApartmentOwnership ownership) {
        apartmentOwnerships.add(ownership);
        ownership.setAdminProfile(this);
    }

    // Method to remove apartment ownership
    public void removeApartmentOwnership(ApartmentOwnership ownership) {
        apartmentOwnerships.remove(ownership);
        ownership.setAdminProfile(null);
    }

    // Getter and setter for the collection
    public Set<ApartmentOwnership> getApartmentOwnerships() {
        return apartmentOwnerships;
    }

    public void setApartmentOwnerships(Set<ApartmentOwnership> apartmentOwnerships) {
        this.apartmentOwnerships = apartmentOwnerships;
    }

    @Override
    public String toString() {
        return "AdminProfile{" +
                "userId=" + userId +
                ", roleLevel='" + roleLevel + '\'' +
                '}';
    }
}
