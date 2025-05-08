package com.myapartment.apartment_management.dto;
import jakarta.validation.constraints.Pattern;

/**
 * DTO specifically for updating maintenance requests
 * Only includes fields that are allowed to be updated
 */
public class UpdateMaintenanceRequestDTO {

    private String description;

    @Pattern(regexp = "plumbing|electrical|HVAC|appliance|general",
            message = "Category must be one of: plumbing, electrical, HVAC, appliance, general")
    private String category;

    @Pattern(regexp = "low|medium|high",
            message = "Priority must be one of: low, medium, high")
    private String priority;

    @Pattern(regexp = "pending|in_progress|completed|canceled",
            message = "Status must be one of: pending, in_progress, completed, canceled")
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