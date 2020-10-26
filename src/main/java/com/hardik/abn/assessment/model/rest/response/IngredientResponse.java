package com.hardik.abn.assessment.model.rest.response;

import com.hardik.abn.assessment.model.entity.Ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientResponse {

	/** The id. */
	private long id;

	/** The ingredient name. */
	private String ingredientName;

	/** The ingredient desciption. */
	private String ingredientDesciption;

	/**
	 * From model.
	 *
	 * @param ingredient the ingredient
	 * @return the ingredient response
	 */
	public static IngredientResponse fromModel(Ingredient ingredient) {
		IngredientResponse ingredientResponse = new IngredientResponse();
		ingredientResponse.setIngredientDesciption(ingredient.getIngredientDesciption());
		ingredientResponse.setIngredientName(ingredient.getIngredientName());
		ingredientResponse.setId(ingredient.getId());
		return ingredientResponse;
	}
}
