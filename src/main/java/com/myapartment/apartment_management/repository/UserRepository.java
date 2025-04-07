package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findById(long id);
}