package com.hardik.abn.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.hardik.abn.assessment.model.entity.Recipe;

/**
 * The Interface RecipeService.
 */
public interface RecipeService {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<Recipe> getAll();

	/**
	 * Find by id.
	 *
	 * @param recipeId the recipe id
	 * @return the optional
	 */
	Optional<Recipe> findById(long recipeId);

	/**
	 * Creates the recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	Recipe createRecipe(Recipe recipe);

	/**
	 * Delete recipe.
	 *
	 * @param recipeId the recipe id
	 */
	void deleteRecipe(long recipeId);

	/**
	 * Update recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	Recipe updateRecipe(Recipe recipe);

	/**
	 * Search.
	 *
	 * @param spec the spec
	 * @return the list
	 */
	List<Recipe> search(Specification<Recipe> spec);

}
