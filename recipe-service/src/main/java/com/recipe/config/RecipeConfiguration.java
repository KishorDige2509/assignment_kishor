package com.recipe.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.recipe.business.enums.DishType;
import com.recipe.integration.domain.Recipe;
import com.recipe.integration.repository.RecipeRepository;

@Configuration
public class RecipeConfiguration {

	@Bean
	CommandLineRunner commandLineRunner(RecipeRepository repository) {
		return args -> {

			Recipe r1 = Recipe.builder().recipeId(1L).dishType(DishType.NON_VEG).ingredients("onion,potatoes,garlic,salmon")
					.instructions("tava").noOfServings(1L).build();
			Recipe r2 = Recipe.builder().recipeId(2L).dishType(DishType.NON_VEG).ingredients("onion,potatoes,garlic,salmon,rice")
					.instructions("tava").noOfServings(2L).build();
			Recipe r3 = Recipe.builder().recipeId(3L).dishType(DishType.NON_VEG).ingredients("onion,potatoes,garlic,salmon,rice")
					.instructions("oven").noOfServings(3L).build();
			Recipe r4 = Recipe.builder().recipeId(4L).dishType(DishType.VEG).ingredients("onion,potatoes,garlic,rice")
					.instructions("tava").noOfServings(4L).build();
			Recipe r5 = Recipe.builder().recipeId(5L).dishType(DishType.VEG).ingredients("onion,potatoes,garlic")
					.instructions("tava").noOfServings(5L).build();

			repository.saveAll(List.of(r1, r2, r3, r4, r5));

		};
	}

}
