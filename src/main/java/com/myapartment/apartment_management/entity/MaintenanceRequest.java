package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resident_user_id")
    private Long residentUserId;

    @Column(name = "flat_id")
    private Long flatId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", length = 30)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "priority", length = 10)
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;

    @Column(name = "status", length = 15)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @OneToMany(mappedBy = "maintenanceRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MaintenanceRequestAssignment> assignments = new HashSet<>();

    // Enums
    public enum Category {
        PLUMBING("PLUMBING"),
        ELECTRICAL("ELECTRICAL"),
        HVAC("HVAC"),
        APPLIANCE("APPLIANCE"),
        GENERAL("GENERAL");

        private final String value;

        Category(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Priority {
        LOW("LOW"),
        MEDIUM("MEDIUM"),
        HIGH("HIGH");

        private final String value;

        Priority(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum Status {
        PENDING("PENDING"),
        IN_PROGRESS("IN_PROGRESS"),
        COMPLETED("COMPLETED"),
        CANCELED("CANCELED");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    // Constructors
    public MaintenanceRequest() {
    }

    public MaintenanceRequest(Long residentUserId, Long flatId, String description, Category category, Priority priority) {
        this.residentUserId = residentUserId;
        this.flatId = flatId;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResidentUserId() {
        return residentUserId;
    }

    public void setResidentUserId(Long residentUserId) {
        this.residentUserId = residentUserId;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Set<MaintenanceRequestAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<MaintenanceRequestAssignment> assignments) {
        this.assignments = assignments;
    }

    // Utility methods for managing bidirectional relationship
    public void addAssignment(MaintenanceRequestAssignment assignment) {
        assignments.add(assignment);
        assignment.setMaintenanceRequest(this);
    }

    public void removeAssignment(MaintenanceRequestAssignment assignment) {
        assignments.remove(assignment);
        assignment.setMaintenanceRequest(null);
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaintenanceRequest that = (MaintenanceRequest) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "MaintenanceRequest{" +
                "id=" + id +
                ", residentUserId=" + residentUserId +
                ", flatId=" + flatId +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", priority=" + priority +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", completedAt=" + completedAt +
                '}';
    }
}