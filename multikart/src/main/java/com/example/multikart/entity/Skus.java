package com.example.multikart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="skus")
public class Skus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="code")
	private String code;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column(name="stock")
	private int stock;
	
	@Column(name="import_price")
	private double importPrice;
	
	@Column(name="export_price")
	private double exportPrice;
	
	@Column(name="value")
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(double importPrice) {
		this.importPrice = importPrice;
	}

	public double getExportPrice() {
		return exportPrice;
	}

	public void setExportPrice(double exportPrice) {
		this.exportPrice = exportPrice;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Skus(int id, String code, Product product, int stock, double importPrice, double exportPrice, String value) {
		super();
		this.id = id;
		this.code = code;
		this.product = product;
		this.stock = stock;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.value = value;
	}

	public Skus() {
		super();
	}

	

	

	
	
}
