package com.myapartment.apartment_management.service;

import com.myapartment.apartment_management.entity.Apartment;
import com.myapartment.apartment_management.entity.Due;
import com.myapartment.apartment_management.entity.Flat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DueService {
    /**
     * Get all dues for a specific apartment
     *
     * @param apartmentId The ID of the apartment
     * @return List of dues for the apartment
     */
    List<Due> getDuesByApartmentId(Long apartmentId);

    /**
     * Create a new due for an apartment and automatically create DuePayments for all flats in the apartment
     *
     * @param apartmentId The ID of the apartment
     * @param cost The cost of the due
     * @param period The period for the due (YYYYMM format)
     * @return The created Due entity
     */
    Due createDue(Long apartmentId, BigDecimal cost, String period);

    /**
     * Get all dues related to a specific flat
     *
     * @param flatId The ID of the flat
     * @return List of dues for the flat
     */
    List<Due> getDuesByFlatId(Long flatId);

    /**
     * Get a specific due by ID
     *
     * @param dueId The ID of the due
     * @return The due if found, otherwise empty
     */
    Optional<Due> getDueById(Long dueId);
}
