package com.example.multikart.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="permissions")
public class Permission extends BaseEntity{
	@Column(name="name")
	private String name;
	
	@Column(name="display_name")
	private String displayName;
	
	@ManyToMany(mappedBy = "permissions",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Roles> roles=new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public Permission(String name, String displayName, Set<Roles> roles) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.roles = roles;
	}

	public Permission() {
		super();
	}
	
	
	
	
}
