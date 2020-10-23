package com.hardik.abn.assessment.model.rest.request;

import com.hardik.abn.assessment.model.entity.Ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientRequest {

	private String ingredientName;
	private String ingredientDesciption;

	public Ingredient toModel() {
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientDesciption(this.ingredientDesciption);
		ingredient.setIngredientName(this.ingredientName);
		return ingredient;
	}
}
