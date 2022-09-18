package com.recipe.business.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.recipe.business.dto.RecipeDTO;
import com.recipe.exception.ContractException;
import com.recipe.exception.TechnicalException;
import com.recipe.util.Constants;
import com.recipe.util.ErrorConstants;
import com.recipe.util.LogUtil;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.This;

@Slf4j
public class RecipeValidator {

	public static final String CLASSNAME = This.class.getSimpleName();

	private RecipeValidator() {
		throw new IllegalStateException("Utility Class");
	}

	public static void validateDto(RecipeDTO dto) throws TechnicalException, ContractException {
		log.info(LogUtil.startLog(CLASSNAME));
		try {

			if (dto == null) {
				log.error(ErrorConstants.DTO_NULL_ERROR);
				throw new ContractException(HttpStatus.EXPECTATION_FAILED, ErrorConstants.DTO_NULL_ERROR, "RecipeDTO");
			}

			if (dto.getDishType() == null) {
				log.error(ErrorConstants.DISH_TYPE_ERROR);
				throw new ContractException(HttpStatus.EXPECTATION_FAILED, ErrorConstants.DISH_TYPE_ERROR, "dishType");
			}

			if (StringUtils.isBlank(dto.getIngredients())) {
				log.error(ErrorConstants.INGREDIENT_ERROR);
				throw new ContractException(HttpStatus.EXPECTATION_FAILED, ErrorConstants.INGREDIENT_ERROR,
						"ingredients");
			}

			if (StringUtils.isBlank(dto.getInstructions())) {
				log.error(ErrorConstants.INSTRUCTIONS_ERROR);
				throw new ContractException(HttpStatus.EXPECTATION_FAILED, ErrorConstants.INSTRUCTIONS_ERROR,
						"instructions");
			}

			if (dto.getNoOfServings() == null || dto.getNoOfServings() < 1) {
				log.error(ErrorConstants.NO_OF_SERVING_ERROR);
				throw new ContractException(HttpStatus.EXPECTATION_FAILED, ErrorConstants.NO_OF_SERVING_ERROR,
						"noOfServings");
			}

		} catch (Exception e) {
			log.error(LogUtil.errorLog(e));
			throw new TechnicalException(Constants.TECHNICAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
