package com.recipe.business.service;

import java.util.Map;

import com.recipe.business.dto.ListingDTO;
import com.recipe.business.dto.RecipeDTO;
import com.recipe.exception.BussinessException;
import com.recipe.exception.ContractException;
import com.recipe.exception.TechnicalException;

public interface RecipeService {

	Map<String, Object> saveRecipe(RecipeDTO dto)
			throws BussinessException, TechnicalException, ContractException;

	Map<String, Object> getByRecipeId(Long recipeId)
			throws BussinessException, TechnicalException, ContractException;

	Map<String, Object> changeStatus(Long recipeId, boolean status)
			throws BussinessException, TechnicalException, ContractException;

	Map<String, Object> listing(ListingDTO listingDto)
			throws BussinessException, TechnicalException, ContractException;

}
