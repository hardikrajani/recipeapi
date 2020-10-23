package com.hardik.abn.assessment.model.rest.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.hardik.abn.assessment.model.entity.Recipe;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeResponse {

	private long id;
	private Date createDate;
	private boolean isVegetarian;
	private int numberOfPerson;
	private String cookingInstruction;
	private List<IngredientResponse> ingredients;
	
	public static RecipeResponse fromModel(Recipe recipe) {
		RecipeResponse recipeResponse = new RecipeResponse();
		recipeResponse.setCookingInstruction(recipe.getCookingInstruction());
		recipeResponse.setCreateDate(recipe.getCreateDate());
		recipeResponse.setId(recipe.getId());
		recipeResponse.setNumberOfPerson(recipe.getNumberOfPerson());
		recipeResponse.setVegetarian(recipe.isVegetarian());
		recipeResponse.setIngredients(recipe.getIngredients().stream().map(IngredientResponse::fromModel).collect(Collectors.toList()));
		return recipeResponse;
	}
}
