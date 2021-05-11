package com.example.multikart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.multikart.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	@Query(value="select u from Users u join fetch u.roles r join fetch r.permissions where u.email like:email")
	Optional<Users> findByNameJWT(@Param("email") String email);
	
	@Query(value="select u.name from Users u where u.email like:email")
	String findByEmail(@Param("email") String email);
}
