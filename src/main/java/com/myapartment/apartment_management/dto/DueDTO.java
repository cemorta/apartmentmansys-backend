package com.myapartment.apartment_management.dto;

import com.myapartment.apartment_management.entity.Due;
import com.myapartment.apartment_management.entity.DuePayment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for Due responses with payment status summary
 */
public class DueDTO {
    private Long id;
    private Long apartmentId;
    private String apartmentName;
    private BigDecimal cost;
    private String period;
    private int totalFlats;
    private int paidFlats;
    private LocalDateTime createdAt;

    public DueDTO() {
    }

    public static DueDTO fromEntity(Due due) {
        DueDTO dueDTO = new DueDTO();
        dueDTO.setId(due.getId());
        dueDTO.setApartmentId(due.getApartment().getId());
        dueDTO.setApartmentName(due.getApartment().getBuildingName());
        dueDTO.setCost(due.getCost());
        dueDTO.setPeriod(due.getPeriod());

        int totalFlats = due.getDuePayments().size();
        int paidFlats = (int) due.getDuePayments().stream()
                .filter(DuePayment::getIsPaymentComplete)
                .count();

        dueDTO.setTotalFlats(totalFlats);
        dueDTO.setPaidFlats(paidFlats);
        dueDTO.setCreatedAt(due.getCreatedAt());

        return dueDTO;
    }

    public static List<DueDTO> fromEntities(List<Due> dues) {
        return dues.stream()
                .map(DueDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getTotalFlats() {
        return totalFlats;
    }

    public void setTotalFlats(int totalFlats) {
        this.totalFlats = totalFlats;
    }

    public int getPaidFlats() {
        return paidFlats;
    }

    public void setPaidFlats(int paidFlats) {
        this.paidFlats = paidFlats;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
