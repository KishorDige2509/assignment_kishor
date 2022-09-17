package com.recipe.util;

public class EndPointReference {

	private EndPointReference() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final String SAVE_RECIPE = "save/Recipe";
	public static final String GET_RECIPE_BY_RECIPE_ID = "get/Recipe/ByRecipeId";
	public static final String CHANGE_STATUS_BY_RECIPE_ID = "changeStatus/ByRecipeId";
	public static final String LISTING_RECIPE = "listing/Recipe";
	
}
