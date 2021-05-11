package com.example.multikart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.multikart.entity.Customer;
import com.example.multikart.entity.Verification;

public interface VerificationRepository extends JpaRepository<Verification, Integer>{
	@Query("from Verification v where v.token like:token ")//select giong cai ma trong database
	Verification findByToken(@Param("token") String token);
}
