package com.demo.service;

import java.util.List;
import com.demo.models.Ingredient;
import com.demo.models.Recipe;

public interface IngredientService {

	List<Ingredient> getAllIngredients();

	Boolean addNewIngredient(Ingredient i,Integer RecipeId);

	Ingredient getIngredientById(int ingid);

	Boolean updateIngredientById(Ingredient f);

	Boolean deleteIngredientById(int ingid);

	Boolean addAllIngredients(List<Ingredient> recipeIngredients, Recipe r);

	List<Ingredient> getAllDistinctIngredients();

}