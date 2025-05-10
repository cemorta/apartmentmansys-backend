package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.MaintenanceRequest;
import com.myapartment.apartment_management.entity.MaintenanceRequestAssignment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaintenanceRequestAssignmentRepository extends JpaRepository<MaintenanceRequestAssignment, Long>, JpaSpecificationExecutor<MaintenanceRequestAssignment> {

  /**
   * Find all assignments for a specific maintenance request
   */
  List<MaintenanceRequestAssignment> findByMaintenanceRequestId(Long requestId);

  /**
   * Find all assignments for a specific staff member
   */
  List<MaintenanceRequestAssignment> findByStaffId(Long staffId);

  /**
   * Find a specific assignment by request ID and staff ID
   */
  Optional<MaintenanceRequestAssignment> findByMaintenanceRequestIdAndStaffId(Long requestId, Long staffId);

  /**
   * Delete all assignments for a specific request
   */
  void deleteByMaintenanceRequestId(Long requestId);

  /**
   * Check if a staff member is assigned to a specific request
   */
  boolean existsByMaintenanceRequestIdAndStaffId(Long requestId, Long staffId);
}