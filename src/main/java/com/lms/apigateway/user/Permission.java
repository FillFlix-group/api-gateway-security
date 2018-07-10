package com.lms.apigateway.user;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Permission implements Serializable {
	protected static final long serialVersionUID = 1L;

	protected Long id;

	protected String description;

	protected String name;

	public Permission() {
	}

	public Permission(Long id, String description, @NotNull String name) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}