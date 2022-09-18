package com.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

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

	Recipe model = new Recipe(1L, DishType.VEG, 2L, "some_inst", "some_ing", true);
	RecipeDTO dto = new RecipeDTO(1L, DishType.VEG, 2L, "some_inst", "some_ing");
	SuccessResponse success = new SuccessResponse("success");

	@Test
	public void saveRecipeTest() throws BussinessException, TechnicalException, ContractException {

		when(repository.save(model)).thenReturn(model);
		final Map<String, Object> map = service.saveRecipe(dto);

		assertEquals(null, map.get(Constants.ERROR));
		assertEquals(success, map.get(Constants.SUCCESS));
		assertEquals(model.getRecipeId(), map.get(Constants.RECIPE_ID));

	}

	@Test
	public void getByRecipeIdTest() throws BussinessException, TechnicalException, ContractException {
		when(repository.findByRecipeIdAndActiveTrue(1L)).thenReturn(model);

		final Map<String, Object> map = service.getByRecipeId(1L);

		assertEquals(dto, map.get(Constants.DTO));
		assertEquals(null, map.get(Constants.ERROR));
		assertEquals(success, map.get(Constants.SUCCESS));

	}

	@Test
	public void changeStatusTest() throws BussinessException, TechnicalException, ContractException {
		when(repository.findByRecipeIdAndActiveTrue(1L)).thenReturn(model);

		final Map<String, Object> map = service.changeStatus(1L, false);

		assertEquals(false, map.get(Constants.STATUS));
		assertEquals(success, map.get(Constants.SUCCESS));
		assertEquals(null, service.getByRecipeId(1L).get(Constants.ERROR));

	}

//	@Test
//	public void getByRecipeIdTest() throws BussinessException, TechnicalException, ContractException {
//		when(repository.findByRecipeIdAndActiveTrue(1L))
//				.thenReturn(new Recipe(1L, DishType.VEG, 2L, "some_inst", "some_ing", true));
//		RecipeDTO dto = new RecipeDTO(1L, DishType.VEG, 2L, "some_inst", "some_ing");
//		SuccessResponse success = new SuccessResponse("success");
//		assertEquals(dto, service.getByRecipeId(1L).get(Constants.DTO));
//		assertEquals(null, service.getByRecipeId(1L).get(Constants.ERROR));
//		assertEquals(success, service.getByRecipeId(1L).get(Constants.SUCCESS));
//
//	}

}
