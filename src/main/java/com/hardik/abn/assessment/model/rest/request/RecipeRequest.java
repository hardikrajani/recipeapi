package com.hardik.abn.assessment.model.rest.request;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.hardik.abn.assessment.model.entity.Recipe;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data
@NoArgsConstructor
public class RecipeRequest {

	private long id;

	/** The name. */
	@Length(min = 3, max = 20)
	private String name;

	/** The is vegetarian. */
	private boolean isVegetarian;

	/** The number of person. */
	@Max(value = 50)
	private int numberOfPerson;

	/** The cooking instruction. */
	@Length(min = 3, max = 100)
	private String cookingInstruction;

	/** The ingredients. */
	@Valid
	@NotEmpty
	@Size(min = 1)
	private List<IngredientRequest> ingredients;

	/**
	 * To model.
	 *
	 * @return the recipe
	 */
	public Recipe toModel() {
		Recipe recipe = new Recipe();
		recipe.setCookingInstruction(this.cookingInstruction);
		recipe.setCreateDate(new Date());
		recipe.setNumberOfPerson(this.numberOfPerson);
		recipe.setVegetarian(this.isVegetarian);
		recipe.setName(this.name);
		recipe.setIngredients(ingredients.stream().map(IngredientRequest::toModel).collect(Collectors.toList()));
		return recipe;
	}

	/**
	 * To model.
	 *
	 * @param recipeId the recipe id
	 * @return the recipe
	 */
	public Recipe toModel(long recipeId) {
		Recipe recipe = this.toModel();
		recipe.setId(recipeId);
		return recipe;
	}
}
