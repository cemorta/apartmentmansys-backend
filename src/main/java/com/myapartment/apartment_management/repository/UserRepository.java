package com.myapartment.apartment_management.repository;

import com.myapartment.apartment_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  User findById(long id);
  Optional<User> findByEmail(String email);
  User findUserByEmail(String email);
  boolean existsByEmail(String email);
}