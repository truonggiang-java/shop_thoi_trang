package com.example.multikart.dto.response;

public class OptionsDtoResponse {
	private Integer id;
	private String name;
	private String[] value;
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
	public String[] getValue() {
		return value;
	}
	public void setValue(String[] value) {
		this.value = value;
	}
	public OptionsDtoResponse() {
		super();
	}
	public OptionsDtoResponse(Integer id, String name, String[] value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
}
