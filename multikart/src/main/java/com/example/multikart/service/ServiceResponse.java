package com.example.multikart.service;

public class ServiceResponse<T> {
	public String status;
	public T data;
	
	public ServiceResponse(String status, T data) {
		super();
		this.status = status;
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public ServiceResponse() {
		super();
	}
	
	
	
	
}
