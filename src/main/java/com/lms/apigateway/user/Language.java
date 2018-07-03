package com.lms.apigateway.user;

import java.io.Serializable;

public class Language implements Serializable {
	protected static final long serialVersionUID = 1L;

	protected Long id;

	protected String code;

	protected String language;

	public Language() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}