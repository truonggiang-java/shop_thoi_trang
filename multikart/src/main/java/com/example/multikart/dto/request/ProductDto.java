package com.example.multikart.dto.request;

import java.util.List;
import java.util.Set;

public class ProductDto {
	private String name;
	private Double discount;
	private String description;
	private Integer category;
	private Integer brands;
	private Integer priority;
	private boolean status;
	private String slug;
	private Set<OptionDto> options;
	private Set<SkusDto> skus;
	public ProductDto(String name, Double discount, String description, Integer category, Integer brands,
			Integer priority, boolean status, String slug, Set<OptionDto> options, Set<SkusDto> skus) {
		super();
		this.name = name;
		this.discount = discount;
		this.description = description;
		this.category = category;
		this.brands = brands;
		this.priority = priority;
		this.status = status;
		this.slug = slug;
		this.options = options;
		this.skus = skus;
	}
	public ProductDto() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getBrands() {
		return brands;
	}
	public void setBrands(Integer brands) {
		this.brands = brands;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public Set<OptionDto> getOptions() {
		return options;
	}
	public void setOptions(Set<OptionDto> options) {
		this.options = options;
	}
	public Set<SkusDto> getSkus() {
		return skus;
	}
	public void setSkus(Set<SkusDto> skus) {
		this.skus = skus;
	}
	
	
	
	

	
	

	
	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	

	
	
	
	

	
	

	
	












	
	

	
	
	
	
	
	
	
}
