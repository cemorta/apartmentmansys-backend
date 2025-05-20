package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.entity.Amenity;
import com.myapartment.apartment_management.entity.AmenityReservation;
import com.myapartment.apartment_management.repository.AmenityRepository;
import com.myapartment.apartment_management.repository.AmenityReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AmenityService {

    private final AmenityRepository amenityRepository;
    private final AmenityReservationRepository amenityReservationRepository;

    @Autowired
    public AmenityService(AmenityRepository amenityRepository, AmenityReservationRepository amenityReservationRepository) {
        this.amenityRepository = amenityRepository;
        this.amenityReservationRepository = amenityReservationRepository;
    }

    public List<LocalTime[]> getAvailableSlots(Long amenityId, LocalDate date) {
        Amenity amenity = amenityRepository.findById(amenityId).orElseThrow();
        List<AmenityReservation> reservations = amenityReservationRepository.findByAmenityIdAndDate(amenityId, date);

        List<LocalTime[]> slots = new ArrayList<>();
        LocalTime time = amenity.getOpeningHour();
        while (time.plusMinutes(amenity.getTimeSliceMinutes()).isBefore(amenity.getClosingHour().plusSeconds(1))) {
            LocalTime end = time.plusMinutes(amenity.getTimeSliceMinutes());
            LocalTime finalTime = time;
            long count = reservations.stream()
                    .filter(r -> r.getStartTime().equals(finalTime))
                    .count();
            if (count < amenity.getMaxPerson()) {
                slots.add(new LocalTime[] { time, end });
            }
            time = end;
        }
        return slots;
    }

}
