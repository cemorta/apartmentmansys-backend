package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.DuePayment;
import com.myapartment.apartment_management.entity.Flat;

import java.time.LocalDateTime;

public class DuePaymentDTO {
    private Long id;

    private DueDTO due;
    private FlatDTO flat;
    private Boolean isPaymentComplete;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;

    public DuePaymentDTO(DuePayment duePayment, boolean includeDue, boolean includeFlat) {
        this.id = duePayment.getId();
        this.isPaymentComplete = duePayment.getIsPaymentComplete();
        this.createdAt = duePayment.getCreatedAt();

        this.paidAt = duePayment.getPaidAt();
        if (duePayment.getFlat() != null && includeFlat) {
            this.flat = new FlatDTO(duePayment.getFlat(), false, false, false);
        }
        if (duePayment.getDue() != null && includeDue) {
            this.due = DueDTO.fromEntity(duePayment.getDue());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DueDTO getDue() {
        return due;
    }

    public void setDue(DueDTO due) {
        this.due = due;
    }

    public Boolean getIsPaymentComplete() {
        return isPaymentComplete;
    }

    public void setIsPaymentComplete(Boolean isPaymentComplete) {
        this.isPaymentComplete = isPaymentComplete;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public FlatDTO getFlat() {
        return flat;
    }

    public void setFlat(FlatDTO flat) {
        this.flat = flat;
    }
}
