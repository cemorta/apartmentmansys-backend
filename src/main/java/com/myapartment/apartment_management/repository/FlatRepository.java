package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.Flat;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FlatRepository extends CrudRepository<Flat, Long> {
  Flat findById(long id);

  List<Flat> findByApartmentId(long apartmentId);
}