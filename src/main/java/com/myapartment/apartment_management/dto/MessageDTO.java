package com.myapartment.apartment_management.dto;

public class MessageDTO {
    private String message;
    private Long userId;

    // Getter ve Setter'lar
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
