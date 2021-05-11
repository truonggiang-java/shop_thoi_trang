package com.example.multikart.convertor;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.multikart.dto.request.CustomerDto;
import com.example.multikart.entity.Customer;

@Component
public class CustomerConvertor {
	@Autowired
	private PasswordEncoder passwordEncoder;
	public Customer converter(CustomerDto customerDto) {
		Customer customer=new Customer();
		customer.setId(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPhone(customerDto.getPhone());
		customer.setAddress(customerDto.getAddress());
		customer.setIsActived(false);
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		customer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		customer.setUpdateAt(new Timestamp(System.currentTimeMillis()));
		return customer;
	}
}
