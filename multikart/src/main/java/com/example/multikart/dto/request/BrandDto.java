package com.example.multikart.dto.request;

public class BrandDto {
	private Integer id;
	private String name;
	private String image;
	private boolean status;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public BrandDto() {
		super();
	}
	public BrandDto(Integer id, String name, String image, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.status = status;
	}
	
	
}
