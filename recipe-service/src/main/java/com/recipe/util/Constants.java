package com.recipe.util;

public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	// ----------------- Object references --------------------
	public static final String RECIPE_ID = "recipeId";
	public static final String STATUS = "status";

	// ----------------- Messages------------------------------
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String DTO = "dto";
	public static final String DTO_LIST = "dtoList";

	public static final Integer FIFTY = 50;

	public static final String TECHNICAL_ERROR = "Some technical exception while performing the operation, please check logs for more info.";
	public static final String TOTAL_COUNT = "totalCount";

	public static final String SEARCH = "search";
	public static final String DEFAULT_SORT = "defaultSort";

}
