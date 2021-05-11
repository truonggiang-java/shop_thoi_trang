package com.example.multikart.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.multikart.convertor.CategoryConvertor;
import com.example.multikart.dto.request.CategoryDto;
import com.example.multikart.entity.Category;
import com.example.multikart.projecttions.category.CategoryByIdProjections;
import com.example.multikart.projecttions.category.CategoryProjecttions;
import com.example.multikart.projecttions.common.SelectCommom;
import com.example.multikart.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryConvertor categoryConvertor;

	public List<CategoryProjecttions> findAll() {
		return categoryRepository.findForAdmin();
	}

	public CategoryByIdProjections findById(Integer id) {
		return categoryRepository.findOne(id);
	}

	public ResponseEntity<Object> update(Integer id, CategoryDto categoryDto) {
		try {
			Category category = categoryRepository.save(categoryConvertor.convertor(categoryDto, id));
			return new ResponseEntity<>(categoryRepository.findOneById(category.getId()), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("có lỗi xảy ra", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Object> created(CategoryDto categoryDto) {
		try {
			Category category = categoryRepository.save(categoryConvertor.convertor(categoryDto, categoryDto.getId()));

			return new ResponseEntity<>(categoryRepository.findByAdmin(category.getId()), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("có lỗi xảy ra", HttpStatus.BAD_REQUEST);
		}
	}

	public List<SelectCommom> findListSelect() {
		return categoryRepository.findListSelect();
	}

	public ResponseEntity<ServiceResponse> delete(Integer id) {
		ServiceResponse response = new ServiceResponse<>();

		if (categoryRepository.findProduct(id).size() > 0) {
			response.setStatus("error");
			response.setData("Danh mục chứa sản phẩm không thể xóa");
		} else if (categoryRepository.findCategories(id).size() > 0) {
			response.setStatus("error");
			response.setData("Danh mục chứa danh mục con không thể xóa");
		} else {
			
			response.setStatus("success");
			categoryRepository.deleteById(id);
			response.setData("Xóa sản phẩm thành công");
		}
		
		return new ResponseEntity<>(response,HttpStatus.OK);

	}

}
