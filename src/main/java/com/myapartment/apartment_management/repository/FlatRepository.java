package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.Flat;
import org.springframework.data.repository.CrudRepository;

public interface FlatRepository extends CrudRepository<Flat, Long> {
  Flat findById(long id);
  
}