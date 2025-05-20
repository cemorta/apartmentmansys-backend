package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.ApartmentDTO;
import com.myapartment.apartment_management.entity.Amenity;
import com.myapartment.apartment_management.repository.AmenityRepository;
import com.myapartment.apartment_management.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/amenities")
public class AmenityController {

    private final AmenityRepository amenityRepository;
    private final AmenityService amenityService;

    AmenityController(AmenityRepository amenityRepository, AmenityService amenityService) {
        this.amenityRepository = amenityRepository;
        this.amenityService = amenityService;
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

    @GetMapping
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

//    @PostMapping("/{id}/reserve")
//    public ResponseEntity<String> reserve(@PathVariable Long id,
//                                          @RequestBody ReservationRequest request) {
//        // Check slot availability, enforce maxPerson, then save
//        // Return appropriate response
//    }
}
