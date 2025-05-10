package com.myapartment.apartment_management.dto;
import jakarta.validation.constraints.Pattern;

/**
 * DTO specifically for updating maintenance requests
 * Only includes fields that are allowed to be updated
 */
public class UpdateMaintenanceRequestDTO {

    private String description;

    @Pattern(regexp = "PLUMBING|ELECTRICAL|HVAC|APPLIANCE|GENERAL",
            message = "Category must be one of: PLUMBING, ELECTRICAL, HVAC, APPLIANCE, GENERAL")
    private String category;

    @Pattern(regexp = "LOW|MEDIUM|HIGH",
            message = "Priority must be one of: LOW, MEDIUM, HIGH")
    private String priority;

    @Pattern(regexp = "PENDING|IN_PROGRESS|COMPLETED|CANCELED",
            message = "Status must be one of: PENDING, IN_PROGRESS, COMPLETED, CANCELED")
    private String status;

    // Constructors
    public UpdateMaintenanceRequestDTO() {
    }

    // Getters and Setters
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
}