package com.myapartment.apartment_management.repository;

import org.springframework.data.repository.CrudRepository;
import com.myapartment.apartment_management.entity.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
  Apartment findById(long id);
}