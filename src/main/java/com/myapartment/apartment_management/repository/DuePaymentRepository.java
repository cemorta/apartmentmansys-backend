package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.DuePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuePaymentRepository extends JpaRepository<DuePayment, Long> {
    /**
     * Find all due payments for a specific flat
     */
    List<DuePayment> findByFlatId(Long flatId);

    /**
     * Find all due payments for a specific due
     */
    List<DuePayment> findByDueId(Long dueId);
}
