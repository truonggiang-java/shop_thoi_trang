package com.example.multikart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.multikart.entity.Customer;


public interface CustomerReposiory extends JpaRepository<Customer, Integer>{

	@Query(value = "select c from Customer c where c.email like:email and c.isActived = true")
    Optional<Customer> findByEmail(@Param("email") String email);
	
}
