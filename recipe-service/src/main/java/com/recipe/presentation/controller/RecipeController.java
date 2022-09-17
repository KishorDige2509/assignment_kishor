package com.recipe.presentation.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.business.dto.ListingDTO;
import com.recipe.business.dto.RecipeDTO;
import com.recipe.business.service.RecipeService;
import com.recipe.exception.BussinessException;
import com.recipe.exception.ContractException;
import com.recipe.exception.TechnicalException;
import com.recipe.util.Constants;
import com.recipe.util.EndPointReference;
import com.recipe.util.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@PostMapping(EndPointReference.SAVE_RECIPE)
	public Map<String, Object> saveRecipe(@Valid @RequestBody RecipeDTO dto,
			@RequestHeader MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException {
		log.info(LogUtil.presentationLogger(EndPointReference.SAVE_RECIPE));
		return recipeService.saveRecipe(dto, headers);
	}

	@PostMapping(EndPointReference.GET_RECIPE_BY_RECIPE_ID)
	public Map<String, Object> getByRecipeId(@RequestParam(Constants.RECIPE_ID) Long recipeId,
			@RequestHeader MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException {
		log.info(LogUtil.presentationLogger(EndPointReference.SAVE_RECIPE));
		return recipeService.getByRecipeId(recipeId, headers);
	}

	@PostMapping(EndPointReference.CHANGE_STATUS_BY_RECIPE_ID)
	public Map<String, Object> changeStatus(@RequestParam(Constants.RECIPE_ID) Long recipeId,
			@RequestParam(Constants.STATUS) boolean status, @RequestHeader MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException {
		log.info(LogUtil.presentationLogger(EndPointReference.SAVE_RECIPE));
		return recipeService.changeStatus(recipeId, status, headers);
	}

	@PostMapping(EndPointReference.LISTING_RECIPE)
	public Map<String, Object> listing(@RequestBody ListingDTO listingDto,
			@RequestHeader MultiValueMap<String, String> headers)
			throws BussinessException, TechnicalException, ContractException {
		log.info(LogUtil.presentationLogger(EndPointReference.SAVE_RECIPE));
		return recipeService.listing(listingDto, headers);
	}

}
