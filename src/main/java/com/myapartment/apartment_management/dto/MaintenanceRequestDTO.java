package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.MaintenanceRequest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MaintenanceRequestDTO {

    private Long id;
    private Long residentUserId;
    private Long flatId;
    private String description;
    private String category;
    private String priority;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private Set<MaintenanceRequestAssignmentDTO> assignments = new HashSet<>();

    // Constructors
    public MaintenanceRequestDTO() {
    }

    /**
     * Create a MaintenanceRequestDTO from a CreateMaintenanceRequestDTO
     * This makes it easy to use the toEntity method after validating a create request
     */
    public MaintenanceRequestDTO(CreateMaintenanceRequestDTO createDTO) {
        this.residentUserId = createDTO.getResidentUserId();
        this.flatId = createDTO.getFlatId();
        this.description = createDTO.getDescription();
        this.category = createDTO.getCategory();
        this.priority = createDTO.getPriority();
        this.status = "PENDING"; // Default status for new requests
    }

    public MaintenanceRequestDTO(MaintenanceRequest entity) {
        this.id = entity.getId();
        this.residentUserId = entity.getResidentUserId();
        this.flatId = entity.getFlatId();
        this.description = entity.getDescription();
        this.category = entity.getCategory() != null ? entity.getCategory().getValue() : null;
        this.priority = entity.getPriority() != null ? entity.getPriority().getValue() : null;
        this.status = entity.getStatus() != null ? entity.getStatus().getValue() : null;
        this.createdAt = entity.getCreatedAt();
        this.completedAt = entity.getCompletedAt();

        if (entity.getAssignments() != null) {
            this.assignments = entity.getAssignments().stream()
                    .map(assignment -> new MaintenanceRequestAssignmentDTO(assignment))
                    .collect(Collectors.toSet());
        }
    }

    // For creating a new maintenance request
    public MaintenanceRequest toEntity() {
        MaintenanceRequest entity = new MaintenanceRequest();
        entity.setResidentUserId(this.residentUserId);
        entity.setFlatId(this.flatId);
        entity.setDescription(this.description);

        // Convert string values to enums
        if (this.category != null) {
            try {
                entity.setCategory(MaintenanceRequest.Category.valueOf(this.category.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Handle invalid category
            }
        }

        if (this.priority != null) {
            try {
                entity.setPriority(MaintenanceRequest.Priority.valueOf(this.priority.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Use default priority
                entity.setPriority(MaintenanceRequest.Priority.MEDIUM);
            }
        }

        // Status will default to PENDING for new requests

        return entity;
    }

    // For updating an existing maintenance request
    public void updateEntity(MaintenanceRequest entity) {
        // Only update fields that can be changed
        if (this.description != null) {
            entity.setDescription(this.description);
        }

        if (this.category != null) {
            try {
                entity.setCategory(MaintenanceRequest.Category.valueOf(this.category.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Keep existing category if invalid
            }
        }

        if (this.priority != null) {
            try {
                entity.setPriority(MaintenanceRequest.Priority.valueOf(this.priority.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Keep existing priority if invalid
            }
        }

        if (this.status != null) {
            try {
                MaintenanceRequest.Status newStatus = MaintenanceRequest.Status.valueOf(this.status.toUpperCase());
                entity.setStatus(newStatus);

                // If status changes to completed, set completedAt
                if (newStatus == MaintenanceRequest.Status.COMPLETED && entity.getCompletedAt() == null) {
                    entity.setCompletedAt(LocalDateTime.now());
                }
                // If status changes from completed, clear completedAt
                else if (newStatus != MaintenanceRequest.Status.COMPLETED) {
                    entity.setCompletedAt(null);
                }
            } catch (IllegalArgumentException e) {
                // Keep existing status if invalid
            }
        }
    }

    /**
     * Static factory method to create a MaintenanceRequestDTO from a CreateMaintenanceRequestDTO
     * This provides an alternative to the constructor approach
     */
    public static MaintenanceRequestDTO fromCreateDTO(CreateMaintenanceRequestDTO createDTO) {
        MaintenanceRequestDTO dto = new MaintenanceRequestDTO();
        dto.setResidentUserId(createDTO.getResidentUserId());
        dto.setFlatId(createDTO.getFlatId());
        dto.setDescription(createDTO.getDescription());
        dto.setCategory(createDTO.getCategory());
        dto.setPriority(createDTO.getPriority());
        dto.setStatus("PENDING"); // Default status for new requests
        return dto;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Set<MaintenanceRequestAssignmentDTO> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<MaintenanceRequestAssignmentDTO> assignments) {
        this.assignments = assignments;
    }
}