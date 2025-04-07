package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.FlatCreateDTO;
import com.myapartment.apartment_management.entity.Apartment;
import com.myapartment.apartment_management.entity.Flat;
import com.myapartment.apartment_management.entity.FlatOwnerProfile;
import com.myapartment.apartment_management.repository.ApartmentRepository;
import com.myapartment.apartment_management.repository.FlatRepository;
import com.myapartment.apartment_management.service.FlatService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flats")
@CrossOrigin(origins = "http://localhost:4200")
public class FlatController {

    private final FlatRepository flatRepository;
    private final FlatService flatService;

    FlatController(FlatRepository flatRepository, FlatService flatService) { this.flatRepository = flatRepository;
        this.flatService = flatService;
    }

    @PostMapping("/create")
    public ResponseEntity<FlatCreateDTO> createFlat(@RequestBody FlatCreateDTO flatCreateDTO) {
        FlatCreateDTO saved = flatService.createFlat(flatCreateDTO);
        return ResponseEntity.ok(saved);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlat(@PathVariable Long id) {
        if (!flatService.existsById(id)) {
            return new ResponseEntity<>("Flat not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        
        flatService.deleteFlat(id);
        return new ResponseEntity<>("Flat successfully deleted", HttpStatus.OK);
    }
}
