package com.recipe.business.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.recipe.annotation.CustomTransactional;
import com.recipe.business.dto.ListingDTO;
import com.recipe.business.dto.RecipeDTO;
import com.recipe.business.dto.SuccessResponse;
import com.recipe.business.service.RecipeService;
import com.recipe.business.validator.RecipeValidator;
import com.recipe.exception.BussinessException;
import com.recipe.exception.ContractException;
import com.recipe.exception.TechnicalException;
import com.recipe.integration.domain.Recipe;
import com.recipe.integration.repository.RecipeRepository;
import com.recipe.util.Constants;
import com.recipe.util.LogUtil;
import com.recipe.util.PaginateUtil;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.This;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	public static final String CLASSNAME = This.class.getSimpleName();

	@Autowired
	private RecipeRepository recipeRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	@CustomTransactional
	public Map<String, Object> saveRecipe(@Valid RecipeDTO dto)
			throws BussinessException, TechnicalException, ContractException {
		Map<String, Object> map = new HashMap<>();
		log.info(LogUtil.startLog(CLASSNAME));

		try {
			RecipeValidator.validateDto(dto);

			Recipe model = recipeRepository.findByRecipeIdAndActiveTrue(dto.getRecipeId());

			if (model == null) {
				model = new Recipe();
				model.setActive(true);
			}

			BeanUtils.copyProperties(dto, model);

			model = recipeRepository.save(model);

			map.put(Constants.RECIPE_ID, model.getRecipeId());
			map.put(Constants.ERROR, null);
			map.put(Constants.SUCCESS, new SuccessResponse(Constants.SUCCESS));

		} catch (Exception e) {
			log.error(LogUtil.errorLog(e));
			throw new TechnicalException(Constants.TECHNICAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
		return map;
	}

	@Override
	public Map<String, Object> getByRecipeId(Long recipeId)
			throws BussinessException, TechnicalException, ContractException {
		Map<String, Object> map = new HashMap<>();
		log.info(LogUtil.startLog(CLASSNAME));

		try {

			Recipe model = recipeRepository.findByRecipeIdAndActiveTrue(recipeId);

			if (model == null) {
				throw new BussinessException(HttpStatus.NOT_FOUND, "Recipe not found", "recipeId");
			}

			RecipeDTO dto = new RecipeDTO();
			BeanUtils.copyProperties(model, dto);

			map.put(Constants.DTO, dto);
			map.put(Constants.ERROR, null);
			map.put(Constants.SUCCESS, new SuccessResponse(Constants.SUCCESS));

		} catch (Exception e) {
			log.error(LogUtil.errorLog(e));
			throw new TechnicalException(Constants.TECHNICAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
		return map;
	}

	@Override
	@CustomTransactional
	public Map<String, Object> changeStatus(Long recipeId, boolean status)
			throws BussinessException, TechnicalException, ContractException {
		Map<String, Object> map = new HashMap<>();
		log.info(LogUtil.startLog(CLASSNAME));

		try {

			Recipe model = recipeRepository.findByRecipeIdAndActiveTrue(recipeId);

			if (model == null) {
				throw new BussinessException(HttpStatus.NOT_FOUND, "Recipe not found", "recipeId");
			}

			model.setActive(status);

			map.put(Constants.STATUS, model.getActive());
			map.put(Constants.ERROR, null);
			map.put(Constants.SUCCESS, new SuccessResponse(Constants.SUCCESS));

		} catch (Exception e) {
			log.error(LogUtil.errorLog(e));
			throw new TechnicalException(Constants.TECHNICAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
		return map;
	}

	@Override
	public Map<String, Object> listing(ListingDTO listingDto)
			throws BussinessException, TechnicalException, ContractException {
		Map<String, Object> map = new HashMap<>();
		log.info(LogUtil.startLog(CLASSNAME));

		try {

			Integer length = listingDto.getLength();

			if (length == null || length < 0) {
				listingDto.setLength(Constants.FIFTY);
			}

			Page<Recipe> list = PaginateUtil.paginate(em, listingDto, Recipe.class);

			List<Recipe> modelList = list.getContent();

			List<RecipeDTO> dtoList = new ArrayList<>();

			for (Recipe model : modelList) {
				RecipeDTO dto = new RecipeDTO();
				BeanUtils.copyProperties(model, dto);

				dtoList.add(dto);
			}

			map.put(Constants.DTO_LIST, dtoList);
			map.put(Constants.TOTAL_COUNT, dtoList.size());

			map.put(Constants.SEARCH, listingDto.getSearch());
			map.put(Constants.DEFAULT_SORT, listingDto.getDefaultSort());

			map.put(Constants.ERROR, null);
			map.put(Constants.SUCCESS, new SuccessResponse(Constants.SUCCESS));

		} catch (Exception e) {
			log.error(LogUtil.errorLog(e));
			throw new TechnicalException(Constants.TECHNICAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info(LogUtil.exitLog(CLASSNAME));
		return map;
	}

}
