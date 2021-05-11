package com.example.multikart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="brand")
public class Brand extends BaseEntity{
	@Column(name="name")
	private String name;
	
	@Column(name="slug")
	private String slug;
	
	@Column(name="status")
	private boolean status;
	
	@Column(name="image")
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Brand(String name, String slug, boolean status, String image) {
		super();
		this.name = name;
		this.slug = slug;
		this.status = status;
		this.image = image;
	}

	public Brand() {
		super();
	}
	
	
}
