package com.myapartment.apartment_management.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myapartment.apartment_management.entity.FlatOwner;

public interface FlatOwnerRepository extends CrudRepository<FlatOwner, Long> {

  List<FlatOwner> findByLastName(String lastName);

  FlatOwner findById(long id);
}