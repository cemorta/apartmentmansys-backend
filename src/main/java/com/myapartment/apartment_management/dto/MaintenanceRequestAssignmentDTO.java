package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.MaintenanceRequest;
import com.myapartment.apartment_management.entity.MaintenanceRequestAssignment;

import java.time.LocalDateTime;

public class MaintenanceRequestAssignmentDTO {

    private Long id;
    private Long requestId;
    private Long staffId;
    private LocalDateTime assignedAt;
    private String notes;

    // Constructors
    public MaintenanceRequestAssignmentDTO() {
    }

    public MaintenanceRequestAssignmentDTO(MaintenanceRequestAssignment entity) {
        this.id = entity.getId();
        this.requestId = entity.getMaintenanceRequest() != null ? entity.getMaintenanceRequest().getId() : null;
        this.staffId = entity.getStaffId();
        this.assignedAt = entity.getAssignedAt();
        this.notes = entity.getNotes();
    }

    // Create new entity method (requires MaintenanceRequest to be passed in)
    public MaintenanceRequestAssignment toEntity(MaintenanceRequest request) {
        MaintenanceRequestAssignment entity = new MaintenanceRequestAssignment();
        entity.setMaintenanceRequest(request);
        entity.setStaffId(this.staffId);
        entity.setNotes(this.notes);

        // Usually assignedAt is set automatically to now(), but allow setting it from DTO if needed
        if (this.assignedAt != null) {
            entity.setAssignedAt(this.assignedAt);
        } else {
            entity.setAssignedAt(LocalDateTime.now());
        }

        return entity;
    }

    // Update existing entity method
    public void updateEntity(MaintenanceRequestAssignment entity) {
        if (this.staffId != null) {
            entity.setStaffId(this.staffId);
        }

        if (this.notes != null) {
            entity.setNotes(this.notes);
        }

        // Usually we don't update assignedAt after creation, but allowing it here if needed
        if (this.assignedAt != null) {
            entity.setAssignedAt(this.assignedAt);
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "MaintenanceRequestAssignmentDTO{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", staffId=" + staffId +
                ", assignedAt=" + assignedAt +
                ", notes='" + notes + '\'' +
                '}';
    }
}