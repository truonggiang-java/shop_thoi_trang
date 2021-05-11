package com.example.multikart.dto.response;

import java.util.HashSet;
import java.util.Set;

import com.example.multikart.entity.Options;
import com.example.multikart.entity.Skus;

public class ProductDetailDtoResponse {
	private Integer id;
	private String name;
	private String slug;
	private Double discount;
	private String description;
	private String image;
	private Set<OptionsDtoResponse> optionsDtos= new HashSet<>();//lay cac phan tu khong trung lap
	private Set<SkusDtoResponse> skusDtos=new HashSet<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Set<OptionsDtoResponse> getOptionsDtos() {
		return optionsDtos;
	}
	public void setOptionsDtos(Set<OptionsDtoResponse> optionsDtos) {
		this.optionsDtos = optionsDtos;
	}
	public Set<SkusDtoResponse> getSkusDtos() {
		return skusDtos;
	}
	public void setSkusDtos(Set<SkusDtoResponse> skusDtos) {
		this.skusDtos = skusDtos;
	}
	public ProductDetailDtoResponse(Integer id, String name, String slug, Double discount, String description,
			String image, Set<OptionsDtoResponse> optionsDtos, Set<SkusDtoResponse> skusDtos) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.discount = discount;
		this.description = description;
		this.image = image;
		this.optionsDtos = optionsDtos;
		this.skusDtos = skusDtos;
	}
	public ProductDetailDtoResponse() {
		
	}
	
	
	
	
	
	
	
	
	
}
