package com.hardik.abn.assessment.service;

import java.util.List;
import java.util.Optional;

import com.hardik.abn.assessment.model.entity.Recipe;

public interface RecipeService {

	List<Recipe> getAll();
	Optional<Recipe> findById(long recipeId);
	List<Recipe> findByIsVegetarian(boolean isVegetarian);
	List<Recipe> findByNumberOfPerson(int numberOfPerson);
	List<Recipe> findByIsVegetarianAndNumberOfPerson(boolean isVegetarian, int numberOfPerson);
	Recipe createRecipe(Recipe recipe);
	void deleteRecipe(long recipeId);
	Recipe updateRecipe(Recipe recipe);
	
}
