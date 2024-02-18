package com.demo.service;

import java.util.List;
import java.util.Set;
import com.demo.models.Ingredient;
import com.demo.models.Recipe;
import com.demo.models.Tag;

public interface RecipeService {

	List<Recipe> getAllRecipes();

	Recipe addNewRecipe(Recipe r,Integer userId);

	Recipe getRecipeById(int rid);

	Boolean updateRecipeById(Recipe u);

	Boolean deleteRecipeById(int rid);

	List<Ingredient> getIngredientsByRecipe(int recipeId);

	//Set<User> getUsersByRecipe(int recipeId);

	Set<Tag> getTagsByRecipe(int recipeId);

}