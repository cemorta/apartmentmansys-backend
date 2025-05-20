package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.entity.DuePayment;

import java.util.List;
import java.util.Optional;

public interface DuePaymentService {
    /**
     * Mark a due payment as complete
     *
     * @param duePaymentId The ID of the due payment
     * @return The updated DuePayment
     */
    DuePayment markPaymentComplete(Long duePaymentId);

    /**
     * Get all due payments for a specific flat
     *
     * @param flatId The ID of the flat
     * @return List of due payments for the flat
     */
    List<DuePayment> getDuePaymentsByFlatId(Long flatId);

    /**
     * Get a specific due payment by ID
     *
     * @param duePaymentId The ID of the due payment
     * @return The due payment if found, otherwise empty
     */
    Optional<DuePayment> getDuePaymentById(Long duePaymentId);

    /**
     * Get all due payments for a specific due
     *
     * @param dueId The ID of the due
     * @return List of due payments for the due
     */
    List<DuePayment> getDuePaymentsByDueId(Long dueId);
}
