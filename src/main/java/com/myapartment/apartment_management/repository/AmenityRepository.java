package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {}

