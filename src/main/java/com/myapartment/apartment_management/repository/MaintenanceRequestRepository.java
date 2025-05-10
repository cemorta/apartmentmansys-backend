package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long>, JpaSpecificationExecutor<MaintenanceRequest> {

  /**
   * Find all maintenance requests for a specific resident
   */
  List<MaintenanceRequest> findByResidentUserId(Long residentUserId);

  /**
   * Find all maintenance requests for a specific flat
   */
  List<MaintenanceRequest> findByFlatId(Long flatId);

  /**
   * Find all maintenance requests with a specific status
   */
  List<MaintenanceRequest> findByStatus(MaintenanceRequest.Status status);

  /**
   * Find all maintenance requests for a specific flat with pagination
   */
  Page<MaintenanceRequest> findByFlatId(Long flatId, Pageable pageable);

  /**
   * Find all maintenance requests for a specific resident with pagination
   */
  Page<MaintenanceRequest> findByResidentUserId(Long residentUserId, Pageable pageable);

  /**
   * Find all maintenance requests with a specific category
   */
  List<MaintenanceRequest> findByCategory(MaintenanceRequest.Category category);

  /**
   * Find all maintenance requests with a specific priority
   */
  List<MaintenanceRequest> findByPriority(MaintenanceRequest.Priority priority);
}