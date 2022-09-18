package com.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.recipe.business.dto.RecipeDTO;
import com.recipe.business.dto.SuccessResponse;
import com.recipe.business.enums.DishType;
import com.recipe.business.service.RecipeService;
import com.recipe.exception.BussinessException;
import com.recipe.exception.ContractException;
import com.recipe.exception.TechnicalException;
import com.recipe.integration.domain.Recipe;
import com.recipe.integration.repository.RecipeRepository;
import com.recipe.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeServiceApplicationTests {

	@Autowired
	private RecipeService service;

	@MockBean
	private RecipeRepository repository;

	@Test
	public void getByRecipeIdTest() throws BussinessException, TechnicalException, ContractException {
		when(repository.findByRecipeIdAndActiveTrue(1L))
				.thenReturn(new Recipe(1L, DishType.VEG, 2L, "some_inst", "some_ing", true));
		RecipeDTO dto = new RecipeDTO(1L, DishType.VEG, 2L, "some_inst", "some_ing");
		SuccessResponse success = new SuccessResponse("success");
		assertEquals(dto, service.getByRecipeId(1L).get(Constants.DTO));
		assertEquals(null, service.getByRecipeId(1L).get(Constants.ERROR));
		assertEquals(success, service.getByRecipeId(1L).get(Constants.SUCCESS));

	}

}
