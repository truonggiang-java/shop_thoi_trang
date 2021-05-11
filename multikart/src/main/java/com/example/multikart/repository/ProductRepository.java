package com.example.multikart.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.multikart.dto.response.OptionSelect;
import com.example.multikart.dto.response.ProductDetailDtoResponse;
import com.example.multikart.dto.response.ProductDtoResponse;
import com.example.multikart.dto.response.ProductSearchResponse;
import com.example.multikart.dto.response.SkusSelect;
import com.example.multikart.entity.Product;
import com.example.multikart.projecttions.product.ProductProjections;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("Select p.id as id, p.name as name, p.priority as priority, p.image as image, p.status as status,b.name as brand,c.name as category "
			+ "FROM Product p inner join Brand b on b.id=p.brand.id inner join Category c on c.id=p.category.id")
	List<ProductProjections> listProductAll();

	@Query("select new com.example.multikart.dto.response.ProductDtoResponse(p.name,p.slug,p.priority,p.discount,min(s.exportPrice),max(s.importPrice),p.image) from Product p inner join Skus s on s.product.id=p.id where p.status = 1 group by p.name")
	List<ProductDtoResponse> listProduct();

	@Query("select p from Product p where p.slug like:slug")
	Product findBySlug(@Param("slug") String slug);
	
	@Query("select new com.example.multikart.dto.response.OptionSelect(o.id,o.name,o.value) FROM Options o inner join Product p on p.id=o.product.id where p.slug like:slug")
	Set<OptionSelect> findOptionSelect(@Param("slug") String slug);

	@Query("select new com.example.multikart.dto.response.SkusSelect(s.value,s.stock,s.code,s.exportPrice) FROM Skus s inner join Product p on p.id=s.product.id where p.slug like:slug")
	Set<SkusSelect> findSkusSelect(@Param("slug") String slug);

	@Query("select new com.example.multikart.dto.response.ProductSearchResponse(p.name,p.slug,p.image,min(s.exportPrice),max(s.importPrice)) from Product p inner join Skus s on s.product.id=p.id where p.status = 1 and p.name like %:name% group by p.name")
	Set<ProductSearchResponse> searchProduct(@Param("name") String name);
}
