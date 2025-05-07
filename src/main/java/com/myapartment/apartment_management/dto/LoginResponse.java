package com.myapartment.apartment_management.dto;

public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean adminProfile;
    private boolean residentProfile;
    private boolean flatOwnerProfile;
    private boolean staffProfile;
    private String token;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAdminProfile() {
        return adminProfile;
    }

    public void setAdminProfile(boolean adminProfile) {
        this.adminProfile = adminProfile;
    }

    public boolean isResidentProfile() {
        return residentProfile;
    }

    public void setResidentProfile(boolean residentProfile) {
        this.residentProfile = residentProfile;
    }

    public boolean isFlatOwnerProfile() {
        return flatOwnerProfile;
    }

    public void setFlatOwnerProfile(boolean flatOwnerProfile) {
        this.flatOwnerProfile = flatOwnerProfile;
    }

    public boolean isStaffProfile() {
        return staffProfile;
    }

    public void setStaffProfile(boolean staffProfile) {
        this.staffProfile = staffProfile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
