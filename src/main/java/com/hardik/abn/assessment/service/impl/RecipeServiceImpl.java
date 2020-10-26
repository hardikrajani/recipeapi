package com.hardik.abn.assessment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.repository.RecipeRepository;
import com.hardik.abn.assessment.service.RecipeService;

/**
 * The Class RecipeServiceImpl.
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	/** The recipe repository. */
	@Autowired
	RecipeRepository recipeRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@Override
	public List<Recipe> getAll() {
		return recipeRepository.findAll();
	}

	/**
	 * Find by id.
	 *
	 * @param recipeId the recipe id
	 * @return the optional
	 */
	@Override
	public Optional<Recipe> findById(long recipeId) {
		return recipeRepository.findById(recipeId);
	}

	/**
	 * Creates the recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	@Override
	public Recipe createRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	/**
	 * Delete recipe.
	 *
	 * @param recipeId the recipe id
	 */
	@Override
	public void deleteRecipe(long recipeId) {
		recipeRepository.deleteById(recipeId);
	}

	/**
	 * Update recipe.
	 *
	 * @param recipe the recipe
	 * @return the recipe
	 */
	@Override
	public Recipe updateRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	/**
	 * Search.
	 *
	 * @param spec the spec
	 * @return the list
	 */
	@Override
	public List<Recipe> search(Specification<Recipe> spec) {
		return recipeRepository.findAll(spec);
	}

}
