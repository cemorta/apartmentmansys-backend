package com.myapartment.apartment_management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapartment.apartment_management.entity.Apartment;
import com.myapartment.apartment_management.repository.ApartmentRepository;

@RestController
@RequestMapping("/api/apartments")
@CrossOrigin(origins = "http://localhost:4200")
public class ApartmentController {

    private final ApartmentRepository apartmentRepository;

    ApartmentController(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping
    public Iterable<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        return apartmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Apartment> addApartment(@RequestBody Apartment apartment) {
        apartment.setId(null); // force Hibernate to treat it as a new record
        if (apartment.getFloor() == 0) {
            return ResponseEntity.badRequest().body(null);
        }

        Apartment saved = apartmentRepository.save(apartment);
        return ResponseEntity.ok(saved);
    }

    // @CrossOrigin(origins = "*")  // Allow from any origin for testing
    // @GetMapping("/all")
    // Iterable<FlatOwner> all() {
    //   return flatOwnerRepository.findAll();
    // }
    
    // private static final Logger log = LoggerFactory.getLogger(FlatOwnerController.class);
    
    // @CrossOrigin(origins = "*")  // Allow from any origin for testing
    // @GetMapping("/greeting")
    // public FlatOwner greeting(@RequestParam(required = false, defaultValue = "World") String name) {
    //     log.info("==== get greeting called with name: {} ====", name);
    //     return new FlatOwner("FirstName", "LastName");
    // }
    
    // // Add a simple test endpoint to verify the controller is working
    // @GetMapping("/test")
    // public String test() {
    //     log.info("==== test endpoint called ====");
    //     return "Controller is working!";
    // }
}
