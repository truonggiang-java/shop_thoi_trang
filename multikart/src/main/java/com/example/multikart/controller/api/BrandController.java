package com.example.multikart.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.multikart.dto.request.BrandRequest;
import com.example.multikart.projecttions.brand.BrandProjectionById;
import com.example.multikart.projecttions.brand.BrandProjections;
import com.example.multikart.service.BrandService;
import com.example.multikart.service.ServiceResponse;

@Controller
@RequestMapping("/api/v1")
public class BrandController {
	@Autowired
	private BrandService brandService;

	@GetMapping("/brands")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<List<BrandProjections>> findAllBrand() {
		return new ResponseEntity<>(brandService.findAllBrand(), HttpStatus.OK);
	}

	@GetMapping("/brands/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<BrandProjectionById> findByIdBrand(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(brandService.findByIdBrand(id), HttpStatus.OK);
	}

	@PostMapping("/brands/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<Object> update(@ModelAttribute BrandRequest branRequest, @PathVariable("id") Integer id,
			HttpServletRequest request, Errors errors) {

		if (errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors().stream()
					.map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.BAD_REQUEST);
		}
		return brandService.update(branRequest, id, request);

	}
	
	@PostMapping("/brands")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Object> create(@ModelAttribute BrandRequest brandRequest,Errors errors,HttpServletRequest request){
		if(errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()),HttpStatus.BAD_REQUEST);
		}
		return brandService.create(brandRequest, request);
	}
	
	@DeleteMapping("/brands/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
		return brandService.delete(id);
	}
}
