package com.myapartment.apartment_management.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "opening_hour", nullable = false)
    private LocalTime openingHour;
    @Column(name = "closing_hour", nullable = false)
    private LocalTime closingHour;

    @Column(name = "max_person", nullable = false)
    private int maxPerson;
    @Column(name = "time_slice_minutes", nullable = false)
    private int timeSliceMinutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(LocalTime openingHour) {
        this.openingHour = openingHour;
    }

    public LocalTime getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(LocalTime closingHour) {
        this.closingHour = closingHour;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public int getTimeSliceMinutes() {
        return timeSliceMinutes;
    }

    public void setTimeSliceMinutes(int timeSliceMinutes) {
        this.timeSliceMinutes = timeSliceMinutes;
    }
}
