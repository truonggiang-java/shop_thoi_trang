package com.example.multikart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.multikart.entity.Brand;
import com.example.multikart.projecttions.brand.BrandProjectionById;
import com.example.multikart.projecttions.brand.BrandProjections;
import com.example.multikart.projecttions.brand.BrandResponse;
import com.example.multikart.projecttions.common.SelectCommom;

public interface BrandRepository extends JpaRepository<Brand, Integer>{
	@Query(value="select b.id as id, b.name as name,b.slug as slug, b.status as status, b.image as image from Brand b",nativeQuery=true)
	List<BrandProjections> findAllBrand();
	
	@Query(value="select b.name as name,b.status as status,b.image as image from Brand b where b.id =:id")
	BrandProjectionById findByIdBrand(@Param("id") Integer id);
	
	@Query(value="select b.name as name,b.slug as slug,b.status as status, b.image as image from Brand b where b.id =:id")
	BrandResponse findOneId(@Param("id") Integer id);
	
	@Query(value="select b.id as id, b.name as name from Brand b")
	List<SelectCommom> findListBrand();
}
