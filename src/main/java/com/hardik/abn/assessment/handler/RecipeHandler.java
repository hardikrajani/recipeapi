package com.hardik.abn.assessment.handler;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.hardik.abn.assessment.exception.RecipeNotFoundException;
import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.model.rest.response.RecipeResponse;
import com.hardik.abn.assessment.repository.specification.RecipeSpecificationsBuilder;
import com.hardik.abn.assessment.service.RecipeService;

@Component
public class RecipeHandler {

	@Autowired
	RecipeService recipeService;

	
	public RecipeResponse createRecipe(Recipe recipe) {
		return RecipeResponse.fromModel(recipeService.createRecipe(recipe));
	}


	public List<RecipeResponse> getAllRecipe() {
		return recipeService.getAll().stream().map(RecipeResponse::fromModel).collect(Collectors.toList());		
	}
	
	public RecipeResponse findById(long recipeId) {
		Optional<Recipe> recipe = recipeService.findById(recipeId);
		if(recipe.isEmpty()) 
			throw new RecipeNotFoundException("recipe is not found for recipeId=" + recipeId);
		
		return RecipeResponse.fromModel(recipe.get());
	}


	public void deleteById(long recipeId) {
		recipeService.deleteRecipe(recipeId);
	}


	public RecipeResponse updateRecipe(Recipe model) {
        this.findById(model.getId());
        return RecipeResponse.fromModel(recipeService.updateRecipe(model));
	}

	public List<RecipeResponse> search(String search) {
        RecipeSpecificationsBuilder builder = new RecipeSpecificationsBuilder();
        Pattern pattern = Pattern.compile("([A-Za-z0-9'_ ]+?)(:|<|>)([A-Za-z0-9_ ]+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        
        Specification<Recipe> spec = builder.build();

		return recipeService.search(spec).stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	}
}
