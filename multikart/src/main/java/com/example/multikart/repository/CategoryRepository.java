package com.example.multikart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.multikart.entity.Category;
import com.example.multikart.projecttions.category.CategoryByIdProjections;
import com.example.multikart.projecttions.category.CategoryProjecttions;
import com.example.multikart.projecttions.category.CategoryResponse;
import com.example.multikart.projecttions.common.SelectCommom;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query(value="select "
				+"c1.id as id,c1.name as name,c1.slug as slug,c1.status as status,c2.name as parentName "+
				"from category c1 left join category c2 on c1.parent_id=c2.id",nativeQuery = true) //mysql
	List<CategoryProjecttions> findForAdmin();
	
	@Query(value="select "
			+"c1.name as name,c1.slug as slug,c1.status as status,c2.name as parentName "+
			"from category c1 left join category c2 on c1.parent_id=c2.id where c1.id=:id",nativeQuery = true) //mysql
	CategoryResponse findOneById(@Param("id") Integer id);
	
	@Query(value="select c.name as name,c.parentId as parentId,c.status as status from Category c where c.id= :id")//java
	CategoryByIdProjections findOne(@Param("id") Integer id);
	
	@Query(value="select "
			+"c1.id as id,c1.name as name,c1.slug as slug,c1.status as status,c2.name as parentName "+
			"from category c1 left join category c2 on c1.parent_id=c2.id where c1.id = :id",nativeQuery = true)
	CategoryProjecttions findByAdmin(@Param("id") Integer id);
	
	@Query(value="select c.id as id, c.name as name from Category c")
	List<SelectCommom> findListSelect();
	
	@Query(value="select p.id from Product p inner join Category c on c.id=p.category.id where c.id =:id")
	List<Integer> findProduct(@Param("id") Integer id);
	
	@Query(value="select c.id from Category c where c.parentId =:id")
	List<Integer> findCategories(@Param("id") Integer id);
	
}
