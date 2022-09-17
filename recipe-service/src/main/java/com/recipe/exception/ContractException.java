package com.recipe.exception;

import org.springframework.http.HttpStatus;

public class ContractException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus errorcode;

	private String description;

	private String parameter;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public HttpStatus getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(HttpStatus errorcode) {
		this.errorcode = errorcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ContractException(HttpStatus errorcode, String description, String parameter) {
		super();
		this.parameter = parameter;
		this.errorcode = errorcode;
		this.description = description;
	}
}
