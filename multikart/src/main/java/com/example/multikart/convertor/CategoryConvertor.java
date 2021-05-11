package com.example.multikart.convertor;

import java.sql.Timestamp;


import org.springframework.stereotype.Component;

import com.example.multikart.dto.request.CategoryDto;
import com.example.multikart.entity.Category;

@Component 
public class CategoryConvertor {
	public Category convertor(CategoryDto categoryDto,Integer id) {
		Category category=new Category();
		if(id !=null) {
			category.setId(id);
		}
		else {
			category.setId(null);
			category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		}
		category.setId(categoryDto.getId());
		category.setName(categoryDto.getName());
		category.setParentId(categoryDto.getParentId());
		category.setStatus(categoryDto.getStatus());
		category.setSlug(categoryDto.getSlug());
		category.setUpdateAt(new Timestamp(System.currentTimeMillis()));
		
		return category;
	}
}
