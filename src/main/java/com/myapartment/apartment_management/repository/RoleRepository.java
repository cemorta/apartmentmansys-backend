package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
  Role findById(long id);

  Role findByName(String name);
}