package com.example.multikart.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;



public class BrandRequest {
	private Integer id;
	
	@NotBlank(message="Tên nhãn hiệu không được rỗng")
	private String name;
	
	private MultipartFile image;
	private String slug;
	@NotNull(message="Trạng thái không được rỗng")
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
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public BrandRequest(Integer id, @NotBlank(message = "Tên nhãn hiệu không được rỗng") String name,
			MultipartFile image, String slug, @NotNull(message = "Trạng thái không được rỗng") boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.slug = slug;
		this.status = status;
	}
	public BrandRequest() {
		super();
	}

	
	
	
}
