package com.myapartment.apartment_management.controller;

import com.myapartment.apartment_management.dto.*;
import com.myapartment.apartment_management.entity.MaintenanceRequest;
import com.myapartment.apartment_management.repository.MaintenanceRequestRepository;
import com.myapartment.apartment_management.service.MaintenanceRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import java.util.Map;

@RestController
@RequestMapping("/api/maintenance")
@CrossOrigin(origins = "http://localhost:4200")
public class MaintenanceRequestController {

    private final MaintenanceRequestRepository maintenanceRequestRepository;
    private final MaintenanceRequestService maintenanceRequestService;

    MaintenanceRequestController(MaintenanceRequestRepository maintenanceRequestRepository,
                                 MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestRepository = maintenanceRequestRepository;
        this.maintenanceRequestService = maintenanceRequestService;
    }

    /**
     * Create a new maintenance request
     */
    @PostMapping
    public ResponseEntity<MaintenanceRequestDTO> createMaintenanceRequest(
            @Valid @RequestBody CreateMaintenanceRequestDTO createDTO) {
        MaintenanceRequestDTO createdRequest = maintenanceRequestService.createRequest(createDTO);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    /**
     * Get a specific maintenance request by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRequestDTO> getMaintenanceRequest(@PathVariable Long id) {
        MaintenanceRequestDTO request = maintenanceRequestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }

    /**
     * Update an existing maintenance request
     */
    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceRequestDTO> updateMaintenanceRequest(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMaintenanceRequestDTO updateDTO) {
        MaintenanceRequestDTO updatedRequest = maintenanceRequestService.updateRequest(id, updateDTO);
        return ResponseEntity.ok(updatedRequest);
    }

    /**
     * Get maintenance requests by resident ID
     */
    @GetMapping("/by-resident/{residentId}")
    public ResponseEntity<List<MaintenanceRequestDTO>> getRequestsByResident(@PathVariable Long residentId) {
        List<MaintenanceRequestDTO> requests = maintenanceRequestService.getRequestsByResidentId(residentId);
        return ResponseEntity.ok(requests);
    }

    /**
     * Get all maintenance requests with pagination
     */
    @GetMapping
    public ResponseEntity<Page<MaintenanceRequestDTO>> getAllMaintenanceRequests(Pageable pageable) {
        Page<MaintenanceRequestDTO> requests = maintenanceRequestService.getAllRequests(pageable);
        return ResponseEntity.ok(requests);
    }

    /**
     * Delete a maintenance request
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceRequest(@PathVariable Long id) {
        maintenanceRequestService.deleteRequest(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Create a new assignment for a maintenance request
     */
    @PostMapping("/{requestId}/assignments")
    public ResponseEntity<MaintenanceRequestAssignmentDTO> createAssignment(
            @PathVariable Long requestId,
            @Valid @RequestBody CreateAssignmentDTO createAssignmentDTO) {
        MaintenanceRequestAssignmentDTO createdAssignment =
                maintenanceRequestService.createAssignment(requestId, createAssignmentDTO);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    /**
     * Delete an assignment from a maintenance request
     */
    @DeleteMapping("/assignments/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long assignmentId) {
        maintenanceRequestService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }

//
//    /**
//     * Get maintenance requests by flat ID
//     */
//    @GetMapping("/by-flat/{flatId}")
//    public ResponseEntity<List<MaintenanceRequestDTO>> getRequestsByFlat(@PathVariable Long flatId) {
//        List<MaintenanceRequestDTO> requests = maintenanceRequestService.getRequestsByFlatId(flatId);
//        return ResponseEntity.ok(requests);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, String>> deleteMaintenanceRequest(@PathVariable Long id) {
//        if (!maintenanceRequestRepository.existsById(id)) {
//            return new ResponseEntity<>(Map.of("message", "Maintenance Request not found with id: " + id), HttpStatus.NOT_FOUND);
//        }
//
//        maintenanceRequestRepository.deleteById(id);
//        return new ResponseEntity<>(Map.of("message", "Maintenance Request successfully deleted"), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<MaintenanceRequest> updateMaintenanceRequest(@PathVariable Long id, @RequestBody MaintenanceRequest maintenanceRequest) {
//        if (!maintenanceRequestRepository.existsById(id)) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        MaintenanceRequest updatedFlat = maintenanceRequestRepository.save(maintenanceRequest);
//        return new ResponseEntity<>(updatedFlat, HttpStatus.OK);
//    }
}
