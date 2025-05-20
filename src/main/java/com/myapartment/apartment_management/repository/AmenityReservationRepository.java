package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.AmenityReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AmenityReservationRepository extends JpaRepository<AmenityReservation, Long> {
    List<AmenityReservation> findByAmenityIdAndDate(Long amenityId, LocalDate date);
}
