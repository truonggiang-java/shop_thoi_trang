package com.example.multikart.entity;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="verification")
public class Verification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="token")
	private String token;
	
	@Column(name="expired_date")
	private LocalDateTime expriedDate;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public LocalDateTime getExpriedDate() {
		return expriedDate;
	}


	public void setExpriedDate(LocalDateTime expriedDate) {
		this.expriedDate = expriedDate;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Verification() {
		this.token=UUID.randomUUID().toString();
		this.expriedDate=LocalDateTime.now().plusDays(1);
	}

	
	
	
}
