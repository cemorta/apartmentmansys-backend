package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.dto.*;
import com.myapartment.apartment_management.entity.MaintenanceRequest;
import com.myapartment.apartment_management.entity.MaintenanceRequestAssignment;
import com.myapartment.apartment_management.repository.MaintenanceRequestAssignmentRepository;
import com.myapartment.apartment_management.repository.MaintenanceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceRequestService {

    private final MaintenanceRequestRepository requestRepository;
    private final MaintenanceRequestAssignmentRepository assignmentRepository;

    @Autowired
    public MaintenanceRequestService(
            MaintenanceRequestRepository requestRepository,
            MaintenanceRequestAssignmentRepository assignmentRepository) {
        this.requestRepository = requestRepository;
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Create a new maintenance request using the CreateMaintenanceRequestDTO
     * Method 1: Using the constructor approach
     */
    @Transactional
    public MaintenanceRequestDTO createRequest(CreateMaintenanceRequestDTO createDTO) {
        // Convert CreateDTO to MaintenanceRequestDTO using constructor
        MaintenanceRequestDTO requestDTO = new MaintenanceRequestDTO(createDTO);

        // Convert to entity
        MaintenanceRequest newRequest = requestDTO.toEntity();

        // Save to database
        MaintenanceRequest savedRequest = requestRepository.save(newRequest);

        // Convert back to DTO for response
        return new MaintenanceRequestDTO(savedRequest);
    }

    /**
     * Update an existing maintenance request
     */
    @Transactional
    public MaintenanceRequestDTO updateRequest(Long requestId, UpdateMaintenanceRequestDTO updateDTO) {
        // Find the existing entity
        MaintenanceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance request not found with id: " + requestId));

        // Create a DTO with the update data
        MaintenanceRequestDTO requestDTO = new MaintenanceRequestDTO();
        requestDTO.setDescription(updateDTO.getDescription());
        requestDTO.setCategory(updateDTO.getCategory());
        requestDTO.setPriority(updateDTO.getPriority());
        requestDTO.setStatus(updateDTO.getStatus());

        // Update the entity
        requestDTO.updateEntity(request);

        // Save the updated entity
        MaintenanceRequest updatedRequest = requestRepository.save(request);

        // Return DTO for response
        return new MaintenanceRequestDTO(updatedRequest);
    }

    /**
     * Get maintenance request by ID
     */
    @Transactional(readOnly = true)
    public MaintenanceRequestDTO getRequestById(Long id) {
        return requestRepository.findById(id)
                .map(MaintenanceRequestDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance request not found with id: " + id));
    }

    /**
     * Get all maintenance requests with pagination
     */
    @Transactional(readOnly = true)
    public Page<MaintenanceRequestDTO> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable)
                .map(MaintenanceRequestDTO::new);
    }

    public Page<MaintenanceRequestDTO> getFiltered(String search, String category, String priority, String status, Pageable pageable) {
        // Create specification to build dynamic query
        Specification<MaintenanceRequest> spec = Specification.where(null);

        // Add search criteria if provided
        if (search != null && !search.trim().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                String searchLike = "%" + search.toLowerCase() + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchLike)
                        // If you have a resident name field or join to a resident entity, add it here
                        // For example:
                        // criteriaBuilder.like(criteriaBuilder.lower(root.join("resident").get("name")), searchLike)
                );
            });
        }

        // Filter by category if provided
        if (category != null && !category.trim().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                try {
                    MaintenanceRequest.Category categoryEnum = MaintenanceRequest.Category.valueOf(category);
                    return criteriaBuilder.equal(root.get("category"), categoryEnum);
                } catch (IllegalArgumentException e) {
                    // Invalid category, ignore this filter
                    return criteriaBuilder.conjunction();
                }
            });
        }

        // Filter by priority if provided
        if (priority != null && !priority.trim().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                try {
                    MaintenanceRequest.Priority priorityEnum = MaintenanceRequest.Priority.valueOf(priority);
                    return criteriaBuilder.equal(root.get("priority"), priorityEnum);
                } catch (IllegalArgumentException e) {
                    // Invalid priority, ignore this filter
                    return criteriaBuilder.conjunction();
                }
            });
        }

        // Filter by status if provided
        if (status != null && !status.trim().isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> {
                try {
                    MaintenanceRequest.Status statusEnum = MaintenanceRequest.Status.valueOf(status);
                    return criteriaBuilder.equal(root.get("status"), statusEnum);
                } catch (IllegalArgumentException e) {
                    // Invalid status, ignore this filter
                    return criteriaBuilder.conjunction();
                }
            });
        }

        // Execute the query with the specification and pageable
        return requestRepository.findAll(spec, pageable)
                .map(MaintenanceRequestDTO::new);
    }

    /**
     * Get maintenance requests by resident ID
     */
    @Transactional(readOnly = true)
    public List<MaintenanceRequestDTO> getRequestsByResidentId(Long residentId) {
        return requestRepository.findByResidentUserId(residentId)
                .stream()
                .map(MaintenanceRequestDTO::new)
                .collect(Collectors.toList());
    }

//    /**
//     * Get maintenance requests by flat ID
//     */
//    @Transactional(readOnly = true)
//    public List<MaintenanceRequestDTO> getRequestsByFlatId(Long flatId) {
//        return requestRepository.findByFlatId(flatId)
//                .stream()
//                .map(MaintenanceRequestDTO::new)
//                .collect(Collectors.toList());
//    }

    /**
     * Delete a maintenance request
     */
    @Transactional
    public void deleteRequest(Long id) {
        if (!requestRepository.existsById(id)) {
            throw new EntityNotFoundException("Maintenance request not found with id: " + id);
        }
        requestRepository.deleteById(id);
    }

    /**
     * Create a new assignment for a maintenance request
     */
    @Transactional
    public MaintenanceRequestAssignmentDTO createAssignment(Long requestId, CreateAssignmentDTO createAssignmentDTO) {
        MaintenanceRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance request not found with id: " + requestId));

        MaintenanceRequestAssignment assignment = new MaintenanceRequestAssignment();
        assignment.setMaintenanceRequest(request);
        assignment.setStaffId(createAssignmentDTO.getStaffId());
        assignment.setNotes(createAssignmentDTO.getNotes());

        MaintenanceRequestAssignment savedAssignment = assignmentRepository.save(assignment);

        // Update the status of the request to in_progress when assigned
        if (request.getStatus() == MaintenanceRequest.Status.PENDING) {
            request.setStatus(MaintenanceRequest.Status.IN_PROGRESS);
            requestRepository.save(request);
        }

        return new MaintenanceRequestAssignmentDTO(savedAssignment);
    }

    /**
     * Delete an assignment
     */
    @Transactional
    public void deleteAssignment(Long assignmentId) {
        if (!assignmentRepository.existsById(assignmentId)) {
            throw new EntityNotFoundException("Assignment not found with id: " + assignmentId);
        }
        assignmentRepository.deleteById(assignmentId);
    }
}