package com.hardik.abn.assessment.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.hardik.abn.assessment.model.entity.Ingredient;
import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.model.rest.response.RecipeResponse;
import com.hardik.abn.assessment.repository.specification.RecipeSpecification;
import com.hardik.abn.assessment.repository.specification.RecipeSpecificationsBuilder;
import com.hardik.abn.assessment.repository.specification.SearchCriteria;

@RunWith(SpringRunner.class)
@SpringBootTest	
class RecipeRepositorySpecificationsTest {

	@MockBean
	private RecipeRepository recipeRepository;
	
    private Recipe samosa;
    private Recipe tea;
	List<Recipe> recipes = new ArrayList<Recipe>();
    
	@BeforeEach
	void setUp() {
		samosa = new Recipe();
		samosa.setName("Samosa");
		samosa.setCookingInstruction("fried potato, covered with flour");
		samosa.setCreateDate(new Date());
		samosa.setNumberOfPerson(3);
		samosa.setVegetarian(false);
		List<Ingredient> samosaIngredients = new ArrayList<>();
		Ingredient samosaIngredient1 = new Ingredient();
		samosaIngredient1.setIngredientDesciption("boiled");
		samosaIngredient1.setIngredientName("Potato");
		samosaIngredients.add(samosaIngredient1);
		samosa.setIngredients(samosaIngredients);
		
		tea = new Recipe();
		tea.setName("Indian Tea");
		tea.setCookingInstruction("well mixed milk with tea leaves");
		tea.setCreateDate(new Date());
		tea.setNumberOfPerson(1);
		tea.setVegetarian(true);
		List<Ingredient> teaIngredients = new ArrayList<>();
		Ingredient teaIngredient1 = new Ingredient();
		teaIngredient1.setIngredientDesciption("milk");
		teaIngredient1.setIngredientName("milk");
		teaIngredients.add(teaIngredient1);
		Ingredient teaIngredient2 = new Ingredient();
		teaIngredient2.setIngredientDesciption("sugar");
		teaIngredient2.setIngredientName("sugar");
		teaIngredients.add(teaIngredient2);
		tea.setIngredients(teaIngredients);		
		
		recipes.add(tea);
		recipes.add(samosa);
	}

	@Test
	public void givenVegetarian_whenGettingListOfRecipe_thenCorrect() {
		RecipeSpecification spec = 
	      new RecipeSpecification(new SearchCriteria("isVegetarian", ":", true));
	    
		Mockito.when(recipeRepository.findAll(spec)).thenReturn(recipes);
		
	    List<Recipe> results = recipeRepository.findAll(spec);
	 
	    List<RecipeResponse> recipeResponses = results.stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	    
	    assertThat(recipeResponses).extracting("isVegetarian").contains(true);
	}

	@Test
	public void givenName_whenGettingListOfRecipe_thenCorrect() {
		RecipeSpecification spec = 
	      new RecipeSpecification(new SearchCriteria("name", ":", "Samosa"));
	    
		Mockito.when(recipeRepository.findAll(spec)).thenReturn(recipes);
		
	    List<Recipe> results = recipeRepository.findAll(spec);
	 
	    List<RecipeResponse> recipeResponses = results.stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	    
	    assertThat(recipeResponses).extracting("name").contains("Samosa");
	}
		
	@Test
	public void givenNumberOfPersonIsGreater_whenGettingListOfRecipe_thenCorrect() {
		RecipeSpecification spec = 
	      new RecipeSpecification(new SearchCriteria("numberOfPerson", ">", 2));
	    
		Mockito.when(recipeRepository.findAll(spec)).thenReturn(recipes);
		
	    List<Recipe> results = recipeRepository.findAll(spec);
	 
	    List<RecipeResponse> recipeResponses = results.stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	    
	    assertThat(recipeResponses).extracting("numberOfPerson").contains(3);
	}
	
	@Test
	public void givenNumberOfPersonIsLess_whenGettingListOfRecipe_thenCorrect() {
		RecipeSpecification spec = 
	      new RecipeSpecification(new SearchCriteria("numberOfPerson", "<", 2));
	    
		Mockito.when(recipeRepository.findAll(spec)).thenReturn(recipes);
		
	    List<Recipe> results = recipeRepository.findAll(spec);
	 
	    List<RecipeResponse> recipeResponses = results.stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	    
	    assertThat(recipeResponses).extracting("numberOfPerson").contains(1);
	}

	@Test
	public void givenNWrongOperation_whenGettingListOfRecipe_thenCorrect() {
		RecipeSpecification spec = 
	      new RecipeSpecification(new SearchCriteria("numberOfPerson", "<=", 2));
	    
		Mockito.when(recipeRepository.findAll(spec)).thenReturn(recipes);
		
	    List<Recipe> results = recipeRepository.findAll(spec);
	 
	    List<RecipeResponse> recipeResponses = results.stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	    
	    assertThat(recipeResponses).extracting("numberOfPerson").contains(1);
	}
	
	@Test
	public void givenNameOrNumberOfPerson_whenGettingListOfRecipes_thenCorrect() {
	    RecipeSpecificationsBuilder builder = new RecipeSpecificationsBuilder();
	 
	    SearchCriteria spec 
	      = new SearchCriteria("name", ":", "Samosa");
	    SearchCriteria spec1 
	      = new SearchCriteria("'numberOfPerson", "<", "2");
	    Specification<Recipe> specs = builder.with(spec).with(spec1).build();
	    
		Mockito.when(recipeRepository.findAll(specs)).thenReturn(recipes);

	    List<Recipe> results = recipeRepository.findAll(specs);
	    List<RecipeResponse> recipeResponses = results.stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	    
	    assertThat(recipeResponses).extracting("name").contains("Samosa");
	    assertThat(recipeResponses).extracting("numberOfPerson").contains(1);
	}

	@Test
	public void givenNameAndNumberOfPerson_whenGettingListOfUsers_thenCorrect() {
	    RecipeSpecificationsBuilder builder = new RecipeSpecificationsBuilder();
	 
	    SearchCriteria spec 
	      = new SearchCriteria("name", ":", "Samosa");
	    SearchCriteria spec1 
	      = new SearchCriteria("numberOfPerson", ">", "2");

	    Specification<Recipe> specs = builder.with(spec).with(spec1).build();
	    
		Mockito.when(recipeRepository.findAll(specs)).thenReturn(recipes);

	    List<Recipe> results = recipeRepository.findAll(specs);
	    List<RecipeResponse> recipeResponses = results.stream().map(RecipeResponse::fromModel).collect(Collectors.toList());
	    
	    assertThat(recipeResponses).extracting("name").contains("Samosa");
	}
	
	@AfterEach
	void deleteEntity() {
		recipeRepository.delete(samosa);
		recipeRepository.delete(tea);
	}
}
