package com.example.multikart.dto.request;

public class SkusDto {
	private String code;
	private Integer stock;
	private Double importPrice;
	private Double exportPrice;
	private String[] value;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(Double importPrice) {
		this.importPrice = importPrice;
	}
	public Double getExportPrice() {
		return exportPrice;
	}
	public void setExportPrice(Double exportPrice) {
		this.exportPrice = exportPrice;
	}
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	public SkusDto(String code, Integer stock, Double importPrice, Double exportPrice, String[] value) {
		super();
		this.code = code;
		this.stock = stock;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.value = value;
	}
	public SkusDto() {
		super();
	}
	
	
	
	
}
