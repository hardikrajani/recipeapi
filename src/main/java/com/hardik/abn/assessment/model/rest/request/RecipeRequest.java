package com.hardik.abn.assessment.model.rest.request;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.hardik.abn.assessment.model.entity.Recipe;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeRequest {

	private Date createDate;
	private boolean isVegetarian;
	private int numberOfPerson;
	private String cookingInstruction;
	private List<IngredientRequest> ingredients;
	
	public Recipe toModel() {
		Recipe recipe = new Recipe();
		recipe.setCookingInstruction(this.cookingInstruction);
		recipe.setCreateDate(this.createDate);
		recipe.setNumberOfPerson(this.numberOfPerson);
		recipe.setVegetarian(this.isVegetarian);
		recipe.setIngredients(ingredients.stream().map(IngredientRequest::toModel).collect(Collectors.toList()));
		return recipe;
	}
	
	public Recipe toModel(long recipeId) {
		Recipe recipe = this.toModel();
		recipe.setId(recipeId);
		return recipe;
	}
}
