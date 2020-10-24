package com.hardik.abn.assessment.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.abn.assessment.handler.RecipeHandler;
import com.hardik.abn.assessment.model.rest.request.RecipeRequest;
import com.hardik.abn.assessment.model.rest.response.RecipeResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipe")
public class RecipeContoller {

	@Autowired
	RecipeHandler recipeHandler;
	
    @GetMapping("/")
    public ResponseEntity<List<RecipeResponse>> getAll() {
        return ResponseEntity.ok(recipeHandler.getAllRecipe());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(recipeHandler.findById(id));
    }
    
    @DeleteMapping("/{id}")
    public void deleteRecipeById(@PathVariable long id) {
        recipeHandler.deleteById(id);
    }
    
    @PostMapping("/")
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest recipeRequest) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(recipeHandler.createRecipe(recipeRequest.toModel()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateAccount(@RequestBody RecipeRequest recipeRequest, @PathVariable long id) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(recipeHandler.updateRecipe(recipeRequest.toModel(id)));
    }
    
    @GetMapping("/vegetarian/{isVegetarian}")
    public ResponseEntity<List<RecipeResponse>> findByIsVegerian(@PathVariable boolean isVegetarian) {
    	return ResponseEntity.ok(recipeHandler.findByIsVegerian(isVegetarian));
    }
    
    @GetMapping("/forperson/{numberOfPerson}")
    public ResponseEntity<List<RecipeResponse>> findByNumberOfPerson(@PathVariable int numberOfPerson) {
    	return ResponseEntity.ok(recipeHandler.findByNumberOfPerson(numberOfPerson));
    }
    
    @GetMapping("/vegetarianforperson/{isVegetarian}/{numberOfPerson}")
    public ResponseEntity<List<RecipeResponse>> findByVegetarianforperson(@PathVariable("isVegetarian") boolean isVegetarian, @PathVariable("numberOfPerson") int numberOfPerson) {
    	return ResponseEntity.ok(recipeHandler.findByIsVegetaranAndNumberOfPerson(isVegetarian, numberOfPerson));
    }
    
    @GetMapping("/search")
    public List<RecipeResponse> search(@RequestParam(value = "search") String search) {
        return recipeHandler.search(search);
    }
}
