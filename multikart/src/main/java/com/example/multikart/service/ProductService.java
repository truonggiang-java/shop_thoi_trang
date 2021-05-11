package com.example.multikart.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.multikart.convertor.ProductConvertor;
import com.example.multikart.dto.request.OptionDto;
import com.example.multikart.dto.request.ProductDto;
import com.example.multikart.dto.request.SkusDto;
import com.example.multikart.dto.response.OptionSelect;
import com.example.multikart.dto.response.OptionsDtoResponse;
import com.example.multikart.dto.response.ProductDetailDtoResponse;
import com.example.multikart.dto.response.ProductDtoResponse;
import com.example.multikart.dto.response.ProductSearchResponse;
import com.example.multikart.dto.response.SkusDtoResponse;
import com.example.multikart.dto.response.SkusSelect;
import com.example.multikart.entity.Product;
import com.example.multikart.projecttions.product.ProductProjections;
import com.example.multikart.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductConvertor productConvertor;
	public ResponseEntity<String> create(ProductDto productDto){
		try {
			productRepository.save(productConvertor.convertor(productDto));
			return new ResponseEntity<>("Them moi thanh cong",HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>("Ok",HttpStatus.BAD_REQUEST);
		}
	}
	
	public List<ProductProjections> listProductAll(){
		return productRepository.listProductAll();
	}
	
	public List<ProductDtoResponse> listProduct(){
		
		return productRepository.listProduct();
	}
	
	public ProductDetailDtoResponse findBySlug(String slug) {
		Product product=productRepository.findBySlug(slug);
		ProductDetailDtoResponse productDetailDtoResponese=new ProductDetailDtoResponse();
		productDetailDtoResponese.setId(product.getId());
		productDetailDtoResponese.setName(product.getName());
		productDetailDtoResponese.setDescription(product.getDescription());
		productDetailDtoResponese.setDiscount(product.getDiscount());
		productDetailDtoResponese.setImage(product.getImage());
		productDetailDtoResponese.setSlug(product.getSlug());
		Set<OptionSelect> optionSelectSet=productRepository.findOptionSelect(slug);
		for(OptionSelect option : optionSelectSet) {
			System.out.println(option.getName());
		}
		Set<SkusSelect> skusSelectSet=productRepository.findSkusSelect(slug); 
		Set<OptionsDtoResponse> optionDtoSet=new HashSet<>();
		Set<SkusDtoResponse> skusDtoSet=new HashSet<>();
		for(OptionSelect optionSelect : optionSelectSet) {
			OptionsDtoResponse optionDto=new OptionsDtoResponse();
			optionDto.setId(optionSelect.getId());
			System.out.println(optionDto.getId());
			optionDto.setName(optionSelect.getName());
			optionDto.setValue(optionSelect.getValue().split("\\,"));
			optionDtoSet.add(optionDto);
			
		}
		for(SkusSelect skusSelect : skusSelectSet) {
			SkusDtoResponse skusDto=new SkusDtoResponse();
			skusDto.setCode(skusSelect.getCode());
			skusDto.setExportPrice(skusSelect.getExportPrice());
			skusDto.setStock(skusSelect.getStock());
			skusDto.setValue(skusSelect.getValue().split("\\,"));
			skusDtoSet.add(skusDto);
			
		}
		productDetailDtoResponese.setOptionsDtos(optionDtoSet);
		productDetailDtoResponese.setSkusDtos(skusDtoSet);
		return productDetailDtoResponese;
	}

	public Set<ProductSearchResponse> searchProduct(String key) {
		
		return productRepository.searchProduct(key);
	}
}
