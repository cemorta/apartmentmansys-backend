package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.Flat;
import com.myapartment.apartment_management.entity.FlatOwnerProfile;
import org.springframework.data.repository.CrudRepository;

public interface FlatOwnerProfileRepository extends CrudRepository<FlatOwnerProfile, Long> {
    java.util.Optional<FlatOwnerProfile> findByUserId(Long userId);
}