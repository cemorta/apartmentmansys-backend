package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.ApartmentDTO;
import com.myapartment.apartment_management.dto.DuePaymentDTO;
import com.myapartment.apartment_management.entity.DuePayment;
import com.myapartment.apartment_management.service.DuePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/due-payments")
public class DuePaymentController {

    private final DuePaymentService duePaymentService;

    @Autowired
    public DuePaymentController(DuePaymentService duePaymentService) {
        this.duePaymentService = duePaymentService;
    }

    /**
     * Mark a due payment as complete
     */
    @PutMapping("/{duePaymentId}/complete")
    public ResponseEntity<DuePaymentDTO> markPaymentComplete(@PathVariable Long duePaymentId) {
        try {
            DuePaymentDTO updatedDuePayment = new DuePaymentDTO(duePaymentService.markPaymentComplete(duePaymentId), true, false);
            return ResponseEntity.ok(updatedDuePayment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all due payments for a specific user
     */
    @GetMapping("/user/{userId}")
    public List<DuePaymentDTO> getDuePaymentsByUserId(@PathVariable Long userId) {
        return StreamSupport.stream(duePaymentService.getDuePaymentsByUserId(userId).spliterator(), false)
                .map(duePayment -> new DuePaymentDTO(duePayment, true, true))
                .collect(Collectors.toList());
    }

    /**
     * Get all due payments for a specific flat
     */
    @GetMapping("/flat/{flatId}")
    public List<DuePaymentDTO> getDuePaymentsByFlatId(@PathVariable Long flatId) {
//        List<DuePayment> duePayments = duePaymentService.getDuePaymentsByFlatId(flatId);
//        return ResponseEntity.ok(duePayments);
        return StreamSupport.stream(duePaymentService.getDuePaymentsByFlatId(flatId).spliterator(), false)
                .map(duePayment -> new DuePaymentDTO(duePayment, true, false))
                .collect(Collectors.toList());
    }

    /**
     * Get a specific due payment by ID
     */
    @GetMapping("/{duePaymentId}")
    public ResponseEntity<DuePayment> getDuePaymentById(@PathVariable Long duePaymentId) {
        return duePaymentService.getDuePaymentById(duePaymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all due payments for a specific due
     */
    @GetMapping("/due/{dueId}")
    public ResponseEntity<List<DuePayment>> getDuePaymentsByDueId(@PathVariable Long dueId) {
        List<DuePayment> duePayments = duePaymentService.getDuePaymentsByDueId(dueId);
        return ResponseEntity.ok(duePayments);
    }
}
