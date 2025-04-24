package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  User findById(long id);
  User findUserByEmail(String email);
}