package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_request_assignments",
        uniqueConstraints = @UniqueConstraint(columnNames = {"request_id", "staff_id"}))
public class MaintenanceRequestAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "request_id", nullable = false)
    private MaintenanceRequest maintenanceRequest;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @Column(name = "notes")
    private String notes;

    // Constructors
    public MaintenanceRequestAssignment() {
    }

    public MaintenanceRequestAssignment(MaintenanceRequest maintenanceRequest, Long staffId) {
        this.maintenanceRequest = maintenanceRequest;
        this.staffId = staffId;
        this.assignedAt = LocalDateTime.now();
    }

    public MaintenanceRequestAssignment(MaintenanceRequest maintenanceRequest, Long staffId, String notes) {
        this.maintenanceRequest = maintenanceRequest;
        this.staffId = staffId;
        this.notes = notes;
        this.assignedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaintenanceRequest getMaintenanceRequest() {
        return maintenanceRequest;
    }

    public void setMaintenanceRequest(MaintenanceRequest maintenanceRequest) {
        this.maintenanceRequest = maintenanceRequest;
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

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaintenanceRequestAssignment that = (MaintenanceRequestAssignment) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "MaintenanceRequestAssignment{" +
                "id=" + id +
                ", requestId=" + (maintenanceRequest != null ? maintenanceRequest.getId() : null) +
                ", staffId=" + staffId +
                ", assignedAt=" + assignedAt +
                ", notes='" + notes + '\'' +
                '}';
    }
}
