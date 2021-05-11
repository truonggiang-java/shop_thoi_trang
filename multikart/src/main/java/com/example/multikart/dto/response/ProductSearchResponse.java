package com.example.multikart.dto.response;

public class ProductSearchResponse {
	private String name;
	private String slug;
	private String image;
	private double priceTo;
	private double priceFrom;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(double priceTo) {
		this.priceTo = priceTo;
	}
	public double getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(double priceFrom) {
		this.priceFrom = priceFrom;
	}
	public ProductSearchResponse(String name, String slug, String image, double priceTo, double priceFrom) {
		super();
		this.name = name;
		this.slug = slug;
		this.image = image;
		this.priceTo = priceTo;
		this.priceFrom = priceFrom;
	}
	public ProductSearchResponse() {
		super();
	}
	
	
}
