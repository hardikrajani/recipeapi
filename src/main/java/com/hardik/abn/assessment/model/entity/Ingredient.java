package com.hardik.abn.assessment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	long id;
	
	@Column(name="ingredient_name")
	String ingredientName;
	
	@Column(name="ingredient_desciption")
	String ingredientDesciption;
}
