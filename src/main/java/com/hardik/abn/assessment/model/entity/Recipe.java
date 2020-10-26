package com.hardik.abn.assessment.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity(name = "recipe")
@EnableAutoConfiguration
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	long id;

	@Column(name = "create_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	Date createDate;

	@Column(name = "vegetarian")
	boolean isVegetarian;

	@Column(name = "number_of_person")
	int numberOfPerson;

	@Column(name = "name")
	String name;

	@Column(name = "cooking_instructions")
	String cookingInstruction;

	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
	@Column(name = "ingredients")
	List<Ingredient> ingredients;
}
