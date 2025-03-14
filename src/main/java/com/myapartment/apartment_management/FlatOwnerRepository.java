package com.myapartment.apartment_management;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FlatOwnerRepository extends CrudRepository<FlatOwner, Long> {

  List<FlatOwner> findByLastName(String lastName);

  FlatOwner findById(long id);
}