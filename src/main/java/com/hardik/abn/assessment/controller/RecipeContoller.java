package com.hardik.abn.assessment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.abn.assessment.handler.RecipeHandler;
import com.hardik.abn.assessment.model.rest.request.RecipeRequest;
import com.hardik.abn.assessment.model.rest.response.RecipeResponse;

import lombok.AllArgsConstructor;

/**
 * The Class RecipeContoller.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipe")
public class RecipeContoller {

	/** The recipehandler instance of handler class */
	@Autowired
	RecipeHandler recipeHandler;

	/**
	 * Gets the all recipe
	 *
	 * @return the all
	 */
	@GetMapping("/")
	public ResponseEntity<List<RecipeResponse>> getAll() {
		return ResponseEntity.ok(recipeHandler.getAllRecipes());
	}

	/**
	 * This method returns unique recipe by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<RecipeResponse> findById(@PathVariable @Valid long id) {
		return ResponseEntity.ok(recipeHandler.findById(id));
	}

	/**
	 * This method deletes recipe by given ID
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	public void deleteRecipeById(@PathVariable @Valid long id) {
		recipeHandler.deleteById(id);
	}

	/**
	 * This method creates recipe with given request body
	 *
	 * @param recipeRequest the recipe request
	 * @return the response entity
	 */
	@PostMapping("/")
	public ResponseEntity<RecipeResponse> createRecipe(@Valid @RequestBody RecipeRequest recipeRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(recipeHandler.createRecipe(recipeRequest.toModel()));
	}

	/**
	 * This method updates recipe of given id with the information provided in
	 * request body
	 *
	 * @param recipeRequest the recipe request
	 * @param id            the id
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<RecipeResponse> updateRecipe(@Valid @RequestBody RecipeRequest recipeRequest,
			@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(recipeHandler.updateRecipe(recipeRequest.toModel(id)));
	}

	/**
	 * This method search for recipe from the database by the given search criteria
	 * Following is example of criteria. isVegetarian:true
	 * isVegetarian:true,numberOfPerson<4
	 *
	 * @param searchCriteria the search criteria
	 * @return the list
	 */
	@GetMapping("/searchByCriteria/{searchCriteria}")
	public List<RecipeResponse> search(@PathVariable String searchCriteria) {
		return recipeHandler.search(searchCriteria);
	}
}
