package com.hardik.abn.assessment.handler;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hardik.abn.assessment.exception.RecipeNotFoundException;
import com.hardik.abn.assessment.model.entity.Ingredient;
import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.model.rest.response.RecipeResponse;
import com.hardik.abn.assessment.repository.specification.RecipeSpecificationsBuilder;
import com.hardik.abn.assessment.service.RecipeService;

/**
 * The Class RecipeHandler.
 */
@Component
public class RecipeHandler {

	@Autowired
	RecipeService recipeService;

	/**
	 * Creates the recipe
	 *
	 * @param recipe the recipe
	 * @return the recipe response
	 */
	public RecipeResponse createRecipe(Recipe recipe) {

		return RecipeResponse.fromModel(recipeService.createRecipe(recipe));
	}

	/**
	 * Gets the all recipes.
	 *
	 * @return the all recipes
	 */
	public List<RecipeResponse> getAllRecipes() {
		return recipeService.getAll().stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	}

	/**
	 * Find by id.
	 *
	 * @param recipeId the recipe id
	 * @return the recipe response
	 */
	public RecipeResponse findById(long recipeId) {
		Optional<Recipe> recipe = recipeService.findById(recipeId);
		if (recipe.isEmpty())
			throw new RecipeNotFoundException("recipe is not found for recipeId=" + recipeId);

		return RecipeResponse.fromModel(recipe.get());
	}

	/**
	 * Delete by id.
	 *
	 * @param recipeId the recipe id
	 */
	public void deleteById(long recipeId) {
		recipeService.deleteRecipe(recipeId);
	}

	/**
	 * Update recipe.
	 *
	 * @param model the model
	 * @return the recipe response
	 */
	public RecipeResponse updateRecipe(Recipe model) {
		Optional<Recipe> recipeOptional = recipeService.findById(model.getId());
		if (recipeOptional.isEmpty())
			throw new RecipeNotFoundException("recipe is not found for recipeId=" + model.getId());

		Recipe recipe = recipeOptional.get();
		recipe.setCookingInstruction(model.getCookingInstruction());
		recipe.setName(model.getName());
		recipe.setNumberOfPerson(model.getNumberOfPerson());
		recipe.setVegetarian(model.isVegetarian());
		for (Ingredient ingredient : model.getIngredients()) {
			this.updateIngredient(ingredient, recipe.getIngredients());
		}

		return RecipeResponse.fromModel(recipeService.updateRecipe(recipe));
	}

	/**
	 * Update ingredient
	 *
	 * @param ingredientParam the ingredient param
	 * @param listIngredients the list ingredients
	 * @return the ingredient
	 */
	private void updateIngredient(Ingredient ingredientParam, List<Ingredient> listIngredients) {
		for (Ingredient ingredient : listIngredients) {
			if (ingredient.getId() == ingredientParam.getId()) {
				ingredient.setIngredientDesciption(ingredientParam.getIngredientDesciption());
				ingredient.setIngredientName(ingredientParam.getIngredientName());
			}
		}
	}

	/**
	 * Search.
	 *
	 * @param search the search
	 * @return the list
	 */
	public List<RecipeResponse> search(String search) {
		RecipeSpecificationsBuilder builder = new RecipeSpecificationsBuilder();
		Pattern pattern = Pattern.compile("([A-Za-z0-9'_ ]+?)(:|<|>)([A-Za-z0-9_ ]+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}

		Specification<Recipe> spec = builder.build();

		return recipeService.search(spec).stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	}
}
