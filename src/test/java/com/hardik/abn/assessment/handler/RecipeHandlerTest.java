package com.hardik.abn.assessment.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.hardik.abn.assessment.exception.RecipeNotFoundException;
import com.hardik.abn.assessment.model.entity.Ingredient;
import com.hardik.abn.assessment.model.entity.Recipe;
import com.hardik.abn.assessment.model.rest.response.RecipeResponse;
import com.hardik.abn.assessment.repository.specification.RecipeSpecification;
import com.hardik.abn.assessment.repository.specification.SearchCriteria;
import com.hardik.abn.assessment.service.RecipeService;
import com.hardik.abn.assessment.service.impl.RecipeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeHandlerTest {

    @TestConfiguration
    static class RecipeServiceImplTestContextConfiguration {
 
        @Bean
        public RecipeService recipeService() {
            return new RecipeServiceImpl();
        }
    }
    
	@MockBean(name = "recipeService")
    private RecipeService recipeService;

    @Autowired
    private RecipeHandler recipeHandler;
 
    private List<Recipe> recipeEntities;
    private Recipe recipeEntity;
    

	@BeforeEach
	public void setUp() {
        recipeEntity = new Recipe();
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

        recipeEntities = new ArrayList<Recipe>();
        recipeEntities.add(recipeEntity);

	}
	
	@Test
	void testCreateRecipe() {
        // given
    	Mockito.when(recipeService.createRecipe(recipeEntity)).thenReturn(recipeEntity);

        // when
        RecipeResponse recipes = recipeHandler.createRecipe(recipeEntity);
     
        // then
        assertThat(recipes.getId()).isEqualTo(1);
	}

	@Test
	void testGetAllRecipe() {
        Mockito.when(recipeService.getAll()).thenReturn(recipeEntities);

        // when
        List<RecipeResponse> recipes = recipeHandler.getAllRecipe();
     
        // then
        assertThat(recipes.size()).isPositive();
	}

	@Test
	void testFindById() {
	   	
        // given
    	Mockito.when(recipeService.findById(1L)).thenReturn(Optional.of(recipeEntity));

        // when
        RecipeResponse recipe = recipeHandler.findById(1L);
     
        // then
        assertThat(recipe.getId()).isEqualTo(1);
	}
	
	@Test
	void testFindById_whenResourceIsNotFound() {
	   	
        // given
    	Mockito.when(recipeService.findById(1L)).thenReturn(Optional.of(recipeEntity));

        // when
        Assertions.assertThrows(RecipeNotFoundException.class, () -> recipeHandler.findById(2L));
     
	}

	@Test
	void testDeleteById() {
	   	
        // given
		Mockito.doNothing().when(recipeService).deleteRecipe(1l);;
		
        // when
        recipeHandler.deleteById(1L);
     
        // then
        verify(recipeService).deleteRecipe(1l);
	}

	@Test
	void testUpdateRecipe() {
        // given
    	Mockito.when(recipeService.findById(1L)).thenReturn(Optional.of(recipeEntity));
    	Mockito.when(recipeService.updateRecipe(recipeEntity)).thenReturn(recipeEntity);

        // when
        RecipeResponse recipes = recipeHandler.updateRecipe(recipeEntity);
     
        // then
        assertThat(recipes.getId()).isEqualTo(1);
	}

	@Test
	void testSearch() {
	   	
        // given
		RecipeSpecification spec = 
			      new RecipeSpecification(new SearchCriteria(false, "isVegetarian", ":", true));

    	Mockito.when(recipeService.search((spec))).thenReturn(recipeEntities);

        // when
        List<RecipeResponse> recipes = recipeHandler.search("isVegetarian:true");
     
        // then
        assertThat(recipes.size()).isZero();
	}
}
