package com.recipe.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe.integration.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

	Recipe findByRecipeIdAndActiveTrue(Long recipeId);

}
