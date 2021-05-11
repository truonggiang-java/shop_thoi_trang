package com.example.multikart.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product extends BaseEntity{
	
	@Column(name="name")
	private String name;
	
	@Column(name="slug")
	private String slug;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image")
	private String image;
	
	@Column(name="discount")
	private Double discount;
	
	@Column(name="image_list")
	private String imageList;
	
	@Column(name="status")
	private boolean status;
	
	@OneToMany(mappedBy = "product",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Options> options= new HashSet<>();
	
	@OneToMany(mappedBy = "product",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Skus> skus= new HashSet<>();

	public Product(String name, String slug, Brand brand, Category category, int priority, String description,
			String image, Double discount, String imageList, boolean status, Set<Options> options, Set<Skus> skus) {
		super();
		this.name = name;
		this.slug = slug;
		this.brand = brand;
		this.category = category;
		this.priority = priority;
		this.description = description;
		this.image = image;
		this.discount = discount;
		this.imageList = imageList;
		this.status = status;
		this.options = options;
		this.skus = skus;
	}

	public Product() {
		super();
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getImageList() {
		return imageList;
	}

	public void setImageList(String imageList) {
		this.imageList = imageList;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<Options> getOptions() {
		return options;
	}

	public void setOptions(Set<Options> options) {
		this.options = options;
	}

	public Set<Skus> getSkus() {
		return skus;
	}

	public void setSkus(Set<Skus> skus) {
		this.skus = skus;
	}
	
	
	
}
