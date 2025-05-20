package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.Due;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DueRepository extends JpaRepository<Due, Long> {
    /**
     * Find all dues for a specific apartment
     */
    List<Due> findByApartmentId(Long apartmentId);

    /**
     * Find all dues related to a specific flat
     */
    @Query("SELECT DISTINCT d FROM Due d JOIN d.duePayments dp WHERE dp.flat.id = :flatId")
    List<Due> findDuesByFlatId(@Param("flatId") Long flatId);
}
