package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.DueDTO;
import com.myapartment.apartment_management.entity.Due;
import com.myapartment.apartment_management.service.DueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dues")
public class DueController {

    private final DueService dueService;

    @Autowired
    public DueController(DueService dueService) {
        this.dueService = dueService;
    }

    /**
     * Get all dues for a specific apartment
     */
    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<DueDTO>> getDuesByApartmentId(@PathVariable Long apartmentId) {
        List<Due> dues = dueService.getDuesByApartmentId(apartmentId);
        return ResponseEntity.ok(DueDTO.fromEntities(dues));
    }

    /**
     * Create a new due for an apartment
     */
    @PostMapping
    public ResponseEntity<DueDTO> createDue(@RequestBody Map<String, Object> payload) {
        try {
            Long apartmentId = Long.valueOf(payload.get("apartmentId").toString());
            BigDecimal cost = new BigDecimal(payload.get("cost").toString());
            String period = payload.get("period").toString();

            Due createdDue = dueService.createDue(apartmentId, cost, period);
            return ResponseEntity.status(HttpStatus.CREATED).body(DueDTO.fromEntity(createdDue));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Get all dues for a specific flat
     */
    @GetMapping("/flat/{flatId}")
    public ResponseEntity<List<DueDTO>> getDuesByFlatId(@PathVariable Long flatId) {
        List<Due> dues = dueService.getDuesByFlatId(flatId);
        return ResponseEntity.ok(DueDTO.fromEntities(dues));
    }

    /**
     * Get a specific due by ID
     */
    @GetMapping("/{dueId}")
    public ResponseEntity<DueDTO> getDueById(@PathVariable Long dueId) {
        return dueService.getDueById(dueId)
                .map(due -> ResponseEntity.ok(DueDTO.fromEntity(due)))
                .orElse(ResponseEntity.notFound().build());
    }
}
