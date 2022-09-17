package com.recipe.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe.integration.domain.IngredientMaster;

@Repository
public interface IngredientMasterRepository extends JpaRepository<IngredientMaster, Long>{

}
