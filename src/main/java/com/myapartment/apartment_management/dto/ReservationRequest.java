package com.myapartment.apartment_management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationRequest {
    public LocalDate date;
    public LocalTime startTime;
    public Long userId;
}
