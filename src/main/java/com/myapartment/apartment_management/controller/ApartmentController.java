package com.myapartment.apartment_management.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.myapartment.apartment_management.dto.ApartmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public List<ApartmentDTO> getAllApartments() {
        return StreamSupport.stream(apartmentRepository.findAll().spliterator(), false)
                .map(apartment -> new ApartmentDTO(apartment, true))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDTO> getApartmentById(@PathVariable Long id) {
        return apartmentRepository.findById(id)
                .map(apartment -> ResponseEntity.ok(new ApartmentDTO(apartment, true)))
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable Long id) {
        if (apartmentRepository.existsById(id)) {
            apartmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
