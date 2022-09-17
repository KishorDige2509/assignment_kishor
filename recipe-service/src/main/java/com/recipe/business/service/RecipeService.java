package com.recipe.business.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.util.MultiValueMap;

import com.recipe.business.dto.ListingDTO;
import com.recipe.business.dto.RecipeDTO;
import com.recipe.exception.BussinessException;
import com.recipe.exception.ContractException;
import com.recipe.exception.TechnicalException;

public interface RecipeService {

	Map<String, Object> saveRecipe(@Valid RecipeDTO dto, MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException;

	Map<String, Object> getByRecipeId(Long recipeId, MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException;

	Map<String, Object> changeStatus(Long recipeId, boolean status, MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException;

	Map<String, Object> listing(ListingDTO listingDto, MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException;

}
