package com.myapartment.apartment_management.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO specifically for creating new maintenance request assignments
 */
public class CreateAssignmentDTO {

    @NotNull(message = "Staff ID is required")
    private Long staffId;

    private String notes;

    // Constructors
    public CreateAssignmentDTO() {
    }

    public CreateAssignmentDTO(Long staffId, String notes) {
        this.staffId = staffId;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}