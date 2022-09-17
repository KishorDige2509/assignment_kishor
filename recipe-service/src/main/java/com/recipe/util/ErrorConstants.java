package com.recipe.util;

public class ErrorConstants {

	private ErrorConstants() {
		throw new IllegalStateException("Utility Class");
	}

	public static final String DTO_NULL_ERROR = "DTO cannot be null";
	public static final String DISH_TYPE_ERROR = "Dish type cannot be null or empty in requesto";
	public static final String INGREDIENT_ERROR = "Ingredients cannot be empty";
	public static final String INSTRUCTIONS_ERROR = "Instructions cannot be empty";
	public static final String NO_OF_SERVING_ERROR = "No of servings cannot be non-zero or negative";

}
