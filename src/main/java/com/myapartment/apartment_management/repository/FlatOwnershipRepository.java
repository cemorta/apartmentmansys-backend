package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.FlatOwnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatOwnershipRepository extends JpaRepository<FlatOwnership, Long> {
    /**
     * Find all due payments for a specific due
     */
    List<FlatOwnership> findFlatOwnershipsByOwner_User_Id(Long userId);
}
