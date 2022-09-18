package com.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.recipe.business.dto.RecipeDTO;
import com.recipe.business.dto.SuccessResponse;
import com.recipe.business.enums.DishType;
import com.recipe.integration.domain.Recipe;
import com.recipe.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeControllerTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();
	SuccessResponse success = new SuccessResponse("success");

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@SuppressWarnings("unchecked")
	@Test
	void saveTest() throws Exception {
		Recipe model = Recipe.builder().dishType(DishType.VEG).noOfServings(23L).ingredients("test_ingd")
				.instructions("test_instructions").build();
		String jsonRequest = om.writeValueAsString(model);

		MvcResult result = mockMvc
				.perform(post("/save/Recipe").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();
		Map<String, Object> map = om.readValue(resultContent, HashMap.class);

		assertNotEquals(null, map.get(Constants.RECIPE_ID));

	}

	@SuppressWarnings("unchecked")
	@Test
	void getByRecipeIdTest() throws Exception {

		MvcResult result = mockMvc
				.perform(post("/get/Recipe/ByRecipeId").param("recipeId", "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();
		Map<String, Object> map = om.readValue(resultContent, HashMap.class);

		RecipeDTO dto = om.readValue(new Gson().toJson(map.get(Constants.DTO)), new TypeReference<RecipeDTO>() {
		});

		assertEquals(1L, dto.getRecipeId());

	}

	@SuppressWarnings("unchecked")
	@Test
	void changeStatusByRecipeIdTest() throws Exception {

		MvcResult result = mockMvc.perform(post("/changeStatus/ByRecipeId").param("recipeId", "2")
				.param("status", "false").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String resultContent = result.getResponse().getContentAsString();
		Map<String, Object> map = om.readValue(resultContent, HashMap.class);

		assertEquals(false, map.get(Constants.STATUS));

	}

}
