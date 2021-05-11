package com.example.multikart.dto.response;

public class SkusSelect {
	private String value;
	private Integer stock;
	private String code;
	private Double exportPrice;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getExportPrice() {
		return exportPrice;
	}
	public void setExportPrice(Double exportPrice) {
		this.exportPrice = exportPrice;
	}
	public SkusSelect(String value, Integer stock, String code, Double exportPrice) {
		super();
		this.value = value;
		this.stock = stock;
		this.code = code;
		this.exportPrice = exportPrice;
	}
	public SkusSelect() {
		super();
	}
	
	
	
}
