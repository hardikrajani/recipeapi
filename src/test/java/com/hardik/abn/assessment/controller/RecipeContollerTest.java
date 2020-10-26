package com.hardik.abn.assessment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hardik.abn.assessment.handler.RecipeHandler;
import com.hardik.abn.assessment.model.entity.Ingredient;
import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.model.rest.request.IngredientRequest;
import com.hardik.abn.assessment.model.rest.request.RecipeRequest;
import com.hardik.abn.assessment.model.rest.response.IngredientResponse;
import com.hardik.abn.assessment.model.rest.response.RecipeResponse;
import com.hardik.abn.assessment.repository.specification.RecipeSpecification;
import com.hardik.abn.assessment.repository.specification.SearchCriteria;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeContoller.class)
class RecipeContollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecipeHandler recipeHandler;

	private List<RecipeResponse> recipes;
	private RecipeResponse recipe;
	private Recipe recipeEntity;
	private RecipeRequest recipeRequest;

	@BeforeEach
	public void setUp() {
		recipe = new RecipeResponse();
		recipe.setId(1);
		recipe.setCookingInstruction("test");
		recipe.setCreateDate(new Date());
		recipe.setNumberOfPerson(2);
		recipe.setVegetarian(true);
		List<IngredientResponse> ingredients = new ArrayList<>();
		IngredientResponse ingredient1 = new IngredientResponse();
		ingredient1.setId(1);
		ingredient1.setIngredientDesciption("test");
		ingredient1.setIngredientName("test");
		IngredientResponse ingredient2 = new IngredientResponse();
		ingredient2.setId(2);
		ingredient2.setIngredientDesciption("test");
		ingredient2.setIngredientName("test");
		ingredients.add(ingredient1);
		ingredients.add(ingredient2);
		recipe.setIngredients(ingredients);

		recipes = new ArrayList<RecipeResponse>();
		recipes.add(recipe);

		Recipe recipeEntity = new Recipe();
		recipeEntity.setId(1);
		recipeEntity.setCookingInstruction("test");
		recipeEntity.setCreateDate(new Date());
		recipeEntity.setNumberOfPerson(2);
		recipeEntity.setVegetarian(true);
		List<Ingredient> ingredientEntities = new ArrayList<>();
		Ingredient ingredientEntity1 = new Ingredient();
		ingredientEntity1.setId(1);
		ingredientEntity1.setIngredientDesciption("test");
		ingredientEntity1.setIngredientName("test");
		Ingredient ingredientEntity2 = new Ingredient();
		ingredientEntity2.setId(2);
		ingredientEntity2.setIngredientDesciption("test");
		ingredientEntity2.setIngredientName("test");
		ingredientEntities.add(ingredientEntity1);
		ingredientEntities.add(ingredientEntity2);
		recipeEntity.setIngredients(ingredientEntities);

		RecipeRequest recipeRequest = new RecipeRequest();
		recipeRequest.setCookingInstruction("test");
		recipeRequest.setNumberOfPerson(2);
		recipeRequest.setVegetarian(true);
		List<IngredientRequest> ingredientRequests = new ArrayList<>();
		IngredientRequest ingredientRequest1 = new IngredientRequest();
		ingredientRequest1.setIngredientDesciption("test");
		ingredientRequest1.setIngredientName("test");
		IngredientRequest ingredientRequest2 = new IngredientRequest();
		ingredientRequest2.setIngredientDesciption("test");
		ingredientRequest2.setIngredientName("test");
		ingredientRequests.add(ingredientRequest1);
		ingredientRequests.add(ingredientRequest2);
		recipeRequest.setIngredients(ingredientRequests);
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testGetAll() throws Exception {

		Mockito.when(recipeHandler.getAllRecipe()).thenReturn(recipes);

		mockMvc.perform(get("/api/v1/recipe/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testFindById() throws Exception {

		long id = 1;
		Mockito.when(recipeHandler.findById(id)).thenReturn(recipe);

		mockMvc.perform(get("/api/v1/recipe/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testDeleteRecipeById() throws Exception {
		long id = 1;

		doNothing().when(recipeHandler).deleteById(id);

		mockMvc.perform(delete("/api/v1/recipe/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testCreateRecipe() throws Exception {

		Mockito.when(recipeHandler.createRecipe(recipeEntity)).thenReturn(recipe);

		mockMvc.perform(
				post("/api/v1/recipe/").content(asJsonString(recipeRequest)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(201));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testUpdateAccount() throws Exception {

		long id = 1;

		Mockito.when(recipeHandler.createRecipe(recipeEntity)).thenReturn(recipe);

		mockMvc.perform(put("/api/v1/recipe/{id}", id).content(asJsonString(recipeRequest))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201));
	}

	@Test
	@WithMockUser(username = "user1", password = "user1Pass", roles = "USER")
	void testSearch() throws Exception {

		RecipeSpecification spec = new RecipeSpecification(new SearchCriteria("isVegetarian", ":", true));

		Mockito.when(recipeHandler.search(spec.toString())).thenReturn(recipes);

		mockMvc.perform(get("/api/v1/recipe/search").param("search", "isVegetarian:true")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(status().is(200));
	}

	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
