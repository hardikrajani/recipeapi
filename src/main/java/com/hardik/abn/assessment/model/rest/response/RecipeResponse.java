package com.hardik.abn.assessment.model.rest.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hardik.abn.assessment.model.entity.Recipe;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeResponse {

	/** The id. */
	private long id;

	/** The name. */
	private String name;

	/** The create date. */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createDate;

	/** The is vegetarian. */
	private boolean isVegetarian;

	/** The number of person. */
	private int numberOfPerson;

	/** The cooking instruction. */
	private String cookingInstruction;

	/** The ingredients. */
	private List<IngredientResponse> ingredients;

	/**
	 * From model.
	 *
	 * @param recipe the recipe
	 * @return the recipe response
	 */
	public static RecipeResponse fromModel(Recipe recipe) {
		RecipeResponse recipeResponse = new RecipeResponse();
		recipeResponse.setCookingInstruction(recipe.getCookingInstruction());
		recipeResponse.setName(recipe.getName());
		recipeResponse.setCreateDate(recipe.getCreateDate());
		recipeResponse.setId(recipe.getId());
		recipeResponse.setNumberOfPerson(recipe.getNumberOfPerson());
		recipeResponse.setVegetarian(recipe.isVegetarian());
		recipeResponse.setIngredients(
				recipe.getIngredients().stream().map(IngredientResponse::fromModel).collect(Collectors.toList()));
		return recipeResponse;
	}
}
