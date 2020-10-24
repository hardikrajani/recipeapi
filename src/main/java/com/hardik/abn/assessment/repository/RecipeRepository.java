package com.hardik.abn.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hardik.abn.assessment.model.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

	List<Recipe> findByIsVegetarian(boolean isVegetarian);
	List<Recipe> findByNumberOfPerson(int numberOfPerson);
	List<Recipe> findByIsVegetarianAndNumberOfPerson(boolean isVegetarian, int numberOfPerson);

}
