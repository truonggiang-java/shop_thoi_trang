package com.example.multikart.convertor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.multikart.dto.request.ProductDto;
import com.example.multikart.entity.Brand;
import com.example.multikart.entity.Category;
import com.example.multikart.entity.Options;
import com.example.multikart.entity.Product;
import com.example.multikart.entity.Skus;

@Component
public class ProductConvertor {
	public Product convertor(ProductDto productDto) {
		Product product=new Product();
		Brand brand=new Brand();
		Category category=new Category();
		category.setId(productDto.getCategory());
		brand.setId(productDto.getBrands());
		product.setName(productDto.getName());
		product.setDiscount(productDto.getDiscount());
		product.setDescription(productDto.getDescription());
		product.setImage("");
		product.setImageList("");
		product.setSlug(productDto.getSlug());
		product.setPriority(productDto.getPriority());
		product.setStatus(productDto.isStatus());
		product.setBrand(brand);
		product.setCategory(category);
		product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		product.setUpdateAt(new Timestamp(System.currentTimeMillis()));
		Set<Options> set=new HashSet<>();
		productDto.getOptions().stream().forEach(optionsDto ->{
			Options options=new Options();
			options.setProduct(product);//lay het cac phan tu tren
			options.setName(optionsDto.getName()); //color,size
			String a="";
			for(String s : optionsDto.getValue()) {
				a+=s+",";
				
			}
			options.setValue(a);
			set.add(options);
		});
		product.setOptions(set);
		Set<Skus> setSkus=new HashSet<>();
		productDto.getSkus().stream().forEach(skusDto ->{
			Skus skus=new Skus();
			skus.setProduct(product);
			skus.setCode(skusDto.getCode());
			skus.setImportPrice(skusDto.getImportPrice());
			skus.setExportPrice(skusDto.getExportPrice());
			skus.setStock(skusDto.getStock());
			String a="";
			for(String s : skusDto.getValue()) {
				a+=s+",";
				
			}
			skus.setValue(a);
			setSkus.add(skus);
		});
		product.setSkus(setSkus);
		return product;
	}
}
