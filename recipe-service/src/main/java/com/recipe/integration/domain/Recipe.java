package com.recipe.integration.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import com.recipe.business.enums.DishType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long recipeId;

	@Enumerated(EnumType.STRING)
	private DishType dishType;

	private Long noOfServings;

	private String instructions;

	@ManyToMany
	@OrderBy
	private Set<IngredientMaster> ingredients;

	private Boolean active = Boolean.TRUE;

}
