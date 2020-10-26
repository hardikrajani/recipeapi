package com.hardik.abn.assessment.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hardik.abn.assessment.model.entity.Ingredient;
import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.repository.RecipeRepository;
import com.hardik.abn.assessment.repository.specification.RecipeSpecification;
import com.hardik.abn.assessment.repository.specification.SearchCriteria;
import com.hardik.abn.assessment.service.RecipeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeServiceImplTest {

	@TestConfiguration
	static class RecipeServiceImplTestContextConfiguration {

		@Bean
		public RecipeService recipeService() {
			return new RecipeServiceImpl();
		}
	}

	@Autowired
	private RecipeService recipeService;

	@MockBean(name = "recipeRepository")
	private RecipeRepository recipeRepository;

	private List<Recipe> recipes;
	private Recipe recipe;

	@BeforeEach
	void setUp() {
		recipe = new Recipe();
		recipe.setId(1);
		recipe.setCookingInstruction("test");
		recipe.setCreateDate(new Date());
		recipe.setNumberOfPerson(2);
		recipe.setVegetarian(true);
		List<Ingredient> ingredients = new ArrayList<>();
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);
		ingredient1.setIngredientDesciption("test");
		ingredient1.setIngredientName("test");
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2);
		ingredient2.setIngredientDesciption("test");
		ingredient2.setIngredientName("test");
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
		recipe.setIngredients(ingredients);

		recipes = new ArrayList<Recipe>();
		recipes.add(recipe);

	}

	@Test
	void whenGetAll_thenReturnRecipeList() {

		Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

		List<Recipe> recipes = recipeService.getAll();

		assertThat(recipes.size()).isPositive();
	}

	@Test
	void whenFindById_thenReturnRecipe() {

		Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

		Optional<Recipe> recipe = recipeService.findById(1L);

		assertThat(recipe).isPresent();
	}

	@Test
	void whenCreateRecipe_thenReturnRecipeList() {

		Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);

		Recipe recipes = recipeService.createRecipe(recipe);

		assertThat(recipes.getId()).isEqualTo(1L);
	}

	@Test
	void whenDeleteRecipe_thenReturnRecipeList() {

		long id = 1;

		recipeService.deleteRecipe(id);

		assertThat(recipeRepository.count()).isZero();
	}

	@Test
	void whenUpdateRecipe_thenReturnRecipeList() {

		Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);

		Recipe recipes = recipeService.updateRecipe(recipe);

		assertThat(recipes.getId()).isEqualTo(1L);
	}

	@Test
	void whenFindAll_thenReturnRecipeList() {

		RecipeSpecification spec = new RecipeSpecification(new SearchCriteria("isVegetarian", ":", true));

		Mockito.when(recipeRepository.findAll(spec)).thenReturn(recipes);

		List<Recipe> recipes = recipeService.search(spec);

		assertThat(recipes.size()).isPositive();
	}
}
