package com.myapartment.apartment_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * DTO specifically for creating new maintenance requests
 * This provides a more focused API for creation with appropriate validations
 */
public class CreateMaintenanceRequestDTO {

    @NotNull(message = "Resident user ID is required")
    private Long residentUserId;

    @NotNull(message = "Flat ID is required")
    private Long flatId;

    @NotBlank(message = "Description is required")
    private String description;

    @Pattern(regexp = "plumbing|electrical|HVAC|appliance|general",
            message = "Category must be one of: plumbing, electrical, HVAC, appliance, general")
    private String category;

    @Pattern(regexp = "low|medium|high",
            message = "Priority must be one of: low, medium, high")
    private String priority = "medium";

    // Constructors
    public CreateMaintenanceRequestDTO() {
    }

    public CreateMaintenanceRequestDTO(Long residentUserId, Long flatId, String description,
                                       String category, String priority) {
        this.residentUserId = residentUserId;
        this.flatId = flatId;
        this.description = description;
        this.category = category;
        this.priority = priority;
    }

    // Getters and Setters
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
}