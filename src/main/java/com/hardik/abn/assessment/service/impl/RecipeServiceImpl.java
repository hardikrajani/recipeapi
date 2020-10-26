package com.hardik.abn.assessment.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.repository.RecipeRepository;
import com.hardik.abn.assessment.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository recipeRepository;

	@Override
	public List<Recipe> getAll() {
		return recipeRepository.findAll();
	}

	@Override
	public Optional<Recipe> findById(long recipeId) {
		return recipeRepository.findById(recipeId);
	}

	@Override
	public Recipe createRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void deleteRecipe(long recipeId) {
		recipeRepository.deleteById(recipeId);
	}

	@Override
	public Recipe updateRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public List<Recipe> search(Specification<Recipe> spec) {
		return recipeRepository.findAll(spec);
	}

}
