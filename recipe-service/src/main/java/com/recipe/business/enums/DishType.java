package com.recipe.business.enums;

/**
 * @author Kishor
 * 
 * @apiNote used for adding specific type of predicates in paginationUtil
 *
 */
public enum DishType {

	VEG(1, "Vegetarian"), NON_VEG(2, "Non-Vegetarian");

	private Integer id;
	private String name;

	private DishType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
