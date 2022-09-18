package com.recipe.business.dto;

import com.recipe.business.enums.DishType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDTO {

	private Long recipeId;

	private DishType dishType;

	private Long noOfServings;

	private String instructions;

	private String ingredients;

}
