package com.hardik.abn.assessment.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

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

        // when
        List<Recipe> recipes = recipeService.getAll();
     
        // then
        assertThat(recipes.size()).isPositive();
    }
    
    @Test
    void whenFindById_thenReturnRecipe() {
    	
        // given
    	Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        // when
        Optional<Recipe> recipe = recipeService.findById(1L);
     
        // then
        assertThat(recipe).isPresent();
    }

    @Test
    void whenFindByIsVegetarian_thenReturnRecipeList() {
        // given
    	boolean isVegetarian = true;
    	Mockito.when(recipeRepository.findByIsVegetarian(isVegetarian)).thenReturn(recipes);

        // when
        List<Recipe> recipes = recipeService.findByIsVegetarian(isVegetarian);
     
        // then
        assertThat(recipes.size()).isPositive();
    }
    
    @Test
    void whenFindByNumberOfPerson_thenReturnRecipeList() {
        // given
    	int numberOfPersons = 2;
    	Mockito.when(recipeRepository.findByNumberOfPerson(numberOfPersons)).thenReturn(recipes);

        // when
        List<Recipe> recipes = recipeService.findByNumberOfPerson(numberOfPersons);
     
        // then
        assertThat(recipes.size()).isPositive();
    }

    @Test
    void whenFindByIsVegetarianAndNumberOfPerson_thenReturnRecipeList() {
        // given
    	int numberOfPersons = 2;
    	boolean isVegetarian = true;
    	Mockito.when(recipeRepository.findByIsVegetarianAndNumberOfPerson(isVegetarian, numberOfPersons)).thenReturn(recipes);

        // when
        List<Recipe> recipes = recipeService.findByIsVegetarianAndNumberOfPerson(isVegetarian, numberOfPersons);
     
        // then
        assertThat(recipes.size()).isPositive();
    }
    
    @Test
    void whenCreateRecipe_thenReturnRecipeList() {
        // given
    	Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);

        // when
        Recipe recipes = recipeService.createRecipe(recipe);
     
        // then
        assertThat(recipes.getId()).isEqualTo(1L);
    }

    @Test
    void whenDeleteRecipe_thenReturnRecipeList() {
        // given
    	long id = 1;

        // when
        recipeService.deleteRecipe(id);
     
        // then
        assertThat(recipeRepository.count()).isZero();
	}
    
    @Test
    void whenUpdateRecipe_thenReturnRecipeList() {
        // given
    	Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);

        // when
        Recipe recipes = recipeService.updateRecipe(recipe);
     
        // then
        assertThat(recipes.getId()).isEqualTo(1L);
    }
    
}
