package com.example.multikart.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.multikart.dto.request.CategoryDto;
import com.example.multikart.projecttions.category.CategoryByIdProjections;
import com.example.multikart.projecttions.category.CategoryProjecttions;
import com.example.multikart.service.CategoryService;
import com.example.multikart.service.ServiceResponse;

@RestController
@RequestMapping(value = "/api/v1")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	// ResponseEntity trả về kiều Json
	@GetMapping(value = "/categories")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<List<CategoryProjecttions>> findAll() {

		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/categories/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<CategoryByIdProjections> findById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
	}

	@PutMapping(value = "/categories/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<Object> update(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDto categoryDto,
			Errors errors) {

		if (errors.hasErrors()) { // hasErrors de neu loi neu loi thi tra ve true

			return new ResponseEntity<>(errors.getAllErrors().stream()
					.map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.BAD_REQUEST);
			// errors.getAllErrors().stream() dang quy ve mot mang neu bi loi
			// .map se bien doi mang thanh cac chuoi
			// objectError la mot doi tuong
			// getDefaultMessage lay ra cai tin nhan cua doi tuong o trong categoryDto
		}
		if(categoryDto.getId() != categoryDto.getParentId()) {
			return categoryService.update(id, categoryDto);
		}
		else {
			return new ResponseEntity<>("Danh mục không được nằm trong chính nó",HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/categories")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Object> create(@Valid @RequestBody CategoryDto categoryDto, Errors errors) {

		if (errors.hasErrors()) { // hasErrors de neu loi neu loi thi tra ve true

			return new ResponseEntity<>(errors.getAllErrors().stream()
					.map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.BAD_REQUEST);
			// errors.getAllErrors().stream() dang quy ve mot mang neu bi loi
			// .map se bien doi mang thanh cac chuoi
			// objectError la mot doi tuong
			// getDefaultMessage lay ra cai tin nhan cua doi tuong o trong categoryDto
		}
		return categoryService.created(categoryDto);
	}
	
	@DeleteMapping(value="/categories/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ServiceResponse> delete(@PathVariable("id") Integer id){
		return categoryService.delete(id);
	}
}