package com.example.multikart.dto.request;

public class CartDto {
	private String name;
	private String code;
	private Double price;
	private Integer quantity;
	private String[] options;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	public CartDto(String name, String code, Double price, Integer quantity, String[] options) {
		super();
		this.name = name;
		this.code = code;
		this.price = price;
		this.quantity = quantity;
		this.options = options;
	}
	public CartDto() {
		super();
	}
	
	
}
