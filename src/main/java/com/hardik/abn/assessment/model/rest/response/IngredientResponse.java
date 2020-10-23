package com.hardik.abn.assessment.model.rest.response;

import com.hardik.abn.assessment.model.entity.Ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientResponse {

	private long id;
	private String ingredientName;
	private String ingredientDesciption;

	public static IngredientResponse fromModel(Ingredient ingredient) {
		IngredientResponse ingredientResponse = new IngredientResponse();
		ingredientResponse.setIngredientDesciption(ingredient.getIngredientDesciption());
		ingredientResponse.setIngredientName(ingredient.getIngredientName());
		ingredientResponse.setId(ingredient.getId());
		return ingredientResponse;
	}
}
