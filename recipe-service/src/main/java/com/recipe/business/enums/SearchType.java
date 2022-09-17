package com.recipe.business.enums;

/**
 * @author Kishor
 * 
 * @apiNote used for controlling search type in paginateUtil
 *
 */
public enum SearchType {

	EQUAL(0, "EQUAL"),
	CONTAINS(1, "CONTAINS"),
	NOT_CONTAINS(2, "NOT_CONTAINS"),
	LIKE(3, "LIKE");

	private Integer id;
	private String name;

	private SearchType(Integer id, String name) {
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
