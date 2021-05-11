package com.example.multikart.dto.request;

public class OptionDto {
	private String name;
	private String[] value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	public OptionDto(String name, String[] value) {
		super();
		this.name = name;
		this.value = value;
	}
	public OptionDto() {
		super();
	}
	
	
	
}
