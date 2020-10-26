package com.hardik.abn.assessment.model.rest.request;

import org.hibernate.validator.constraints.Length;

import com.hardik.abn.assessment.model.entity.Ingredient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new ingredient request.
 */
@NoArgsConstructor
public class IngredientRequest {

	private long id;

	/** The ingredient name. */
	@Length(min = 3, max = 20)
	private String ingredientName;

	/** The ingredient desciption. */
	@Length(min = 3, max = 100)
	private String ingredientDesciption;

	/**
	 * To model.
	 *
	 * @return the ingredient
	 */
	public Ingredient toModel() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(this.id);
		ingredient.setIngredientDesciption(this.ingredientDesciption);
		ingredient.setIngredientName(this.ingredientName);
		return ingredient;
	}
}
