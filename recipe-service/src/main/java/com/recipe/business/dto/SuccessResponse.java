package com.recipe.business.dto;

import lombok.Data;

@Data
public class SuccessResponse {

	public final String status = "200";
	public String message;

	public SuccessResponse(String message) {
		super();
		this.message = message;
	}

	public SuccessResponse() {
	}

}
