package com.example.multikart.dto.response;

public class ProductDtoResponse {
	private String name;
	private String slug;
	private Integer priority;
	private Double discount;
	private Double priceFrom;
	private Double priceTo;
	private String image;
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
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(Double priceFrom) {
		this.priceFrom = priceFrom;
	}
	public Double getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(Double priceTo) {
		this.priceTo = priceTo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public ProductDtoResponse(String name, String slug, Integer priority, Double discount, Double priceFrom,
			Double priceTo, String image) {
		super();
		this.name = name;
		this.slug = slug;
		this.priority = priority;
		this.discount = discount;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.image = image;
	}
	public ProductDtoResponse() {
		super();
	}
	
	
	
	
	
}
