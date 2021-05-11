package com.example.multikart.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multikart.dto.request.ProductDto;
import com.example.multikart.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	@Autowired
	public ProductService productServcice;
	
	@PostMapping(value="/products")
	@PreAuthorize("hasAuthority('CREATE_PRODUCT')")
	public ResponseEntity<String> create(@RequestBody ProductDto productDto){
		return productServcice.create(productDto);
	}
}
