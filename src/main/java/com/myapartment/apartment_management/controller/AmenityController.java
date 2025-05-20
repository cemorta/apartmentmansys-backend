package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.ReservationRequest;
import com.myapartment.apartment_management.entity.Amenity;
import com.myapartment.apartment_management.entity.AmenityReservation;
import com.myapartment.apartment_management.entity.User;
import com.myapartment.apartment_management.repository.AmenityRepository;
import com.myapartment.apartment_management.repository.AmenityReservationRepository;
import com.myapartment.apartment_management.service.AmenityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    private final AmenityRepository amenityRepository;
    private final AmenityService amenityService;
    private final AmenityReservationRepository amenityReservationRepository;

    AmenityController(AmenityRepository amenityRepository, AmenityService amenityService, AmenityReservationRepository amenityReservationRepository) {
        this.amenityRepository = amenityRepository;
        this.amenityService = amenityService;
        this.amenityReservationRepository = amenityReservationRepository;
    }

    @PostMapping
    public Amenity createAmenity(@RequestBody Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    @GetMapping("/{id}/slots")
    public List<LocalTime[]> getAvailableSlots(
            @PathVariable Long id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return amenityService.getAvailableSlots(id, date);
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserve(
            @PathVariable Long id,
            @RequestBody ReservationRequest request) {

        Optional<Amenity> optionalAmenity = amenityRepository.findById(id);
        if (optionalAmenity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Amenity not found.");
        }

        Amenity amenity = optionalAmenity.get();

        LocalDate date = request.date;
        LocalTime startTime = request.startTime;
        LocalTime endTime = startTime.plusMinutes(amenity.getTimeSliceMinutes());

        // Check if the slot is in the available slots list
        List<LocalTime[]> availableSlots = amenityService.getAvailableSlots(id, date);

        boolean isSlotAvailable = availableSlots.stream()
                .anyMatch(slot -> slot[0].equals(startTime) && slot[1].equals(endTime));

        if (!isSlotAvailable) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Selected time slot is wrong or no longer available.");
        }

        // Optional: Check if user already reserved this slot
        boolean alreadyReserved = amenityReservationRepository
                .findByAmenityIdAndDate(id, date).stream()
                .anyMatch(r -> r.getStartTime().equals(startTime) && Objects.equals(r.getUser().getId(), request.userId));

        if (alreadyReserved) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("You have already reserved this slot.");
        }

        // Save reservation
        AmenityReservation reservation = new AmenityReservation();
        reservation.setAmenity(amenity);
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        User newUser = new User();
        newUser.setId(request.userId);
        reservation.setUser(newUser);

        amenityReservationRepository.save(reservation);

        return ResponseEntity.ok("Reservation successful.");
    }

    @GetMapping
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }
}
