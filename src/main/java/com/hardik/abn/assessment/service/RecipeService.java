package com.hardik.abn.assessment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.hardik.abn.assessment.model.entity.Recipe;

public interface RecipeService {

	List<Recipe> getAll();
	Optional<Recipe> findById(long recipeId);
	Recipe createRecipe(Recipe recipe);
	void deleteRecipe(long recipeId);
	Recipe updateRecipe(Recipe recipe);
	List<Recipe> search(Specification<Recipe> spec);
	
}
