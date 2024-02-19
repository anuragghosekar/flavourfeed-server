package com.demo.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.RecipeDao;
import com.demo.dao.UserDao;
import com.demo.models.Ingredient;
import com.demo.models.Recipe;
import com.demo.models.Tag;
import com.demo.models.User;


@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDao rdao;
	@Autowired
	private UserDao udao;
//	@Autowired
//	private IngredientService iservice;


	public List<Recipe> getAllRecipes() {
		try {
		return rdao.findAll();
		}catch(Exception e) {
			System.out.println(e);
			return null;
			
		}
	}

	@Override
	public Boolean deleteRecipeById(int rid) {
		if (rdao.existsById(rid)) {
			rdao.deleteById(rid);
			return true;
		}
		return false;	
	}

	@Override
	public Boolean updateRecipeById(Recipe r) {
		Optional<Recipe> op=rdao.findById(r.getRecipeId());
		if(op.isPresent()) {
			Recipe r1=op.get();
			r1.setRecipeName(r.getRecipeName());
			r1.setInstructions(r.getInstructions());
			r1.setRecipeType(r.getRecipeType());
			r1.setCookTime(r.getCookTime());
			r1.setRecipeDescription(r.getRecipeDescription());
			r1.setTotalCalories(r.getTotalCalories());
			r1.setRecipeTags(r.getRecipeTags());
			r1.setRecipeIngredients(r.getRecipeIngredients());
			if(rdao.save(r1)!=null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Recipe addNewRecipe(Recipe r, Integer userId) {
	    try {
	    	User user = udao.findById(userId).orElse(null);
	    	r.setUser(user);
	        Recipe recipe=rdao.save(r);
	        return recipe;
	    } catch (Exception e) {
	    	System.out.println(e.getMessage());
	        return null;
	    }
	}


	@Override
	public Recipe getRecipeById(int rid) {
		Optional<Recipe> op=rdao.findById(rid);
		 if(op.isPresent()) {
			 return op.get();
		 }
		 return null;
	}
	
//	public Set<User> getUsersByRecipe(int recipeId) {
//	    Recipe recipe = rdao.findById(recipeId).orElse(null);
//	    if (recipe != null) {
//	        return recipe.getUserRecipes();
//	    } else {
//	        return null;
//	    }
//	}

	public Set<Tag> getTagsByRecipe(int recipeId) {
	    Recipe recipe = rdao.findById(recipeId).orElse(null);
	    if (recipe != null) {
	        return recipe.getRecipeTags();
	    } else {
	        return null;
	    }
	}

	public List<Ingredient> getIngredientsByRecipe(int recipeId) {
	    Recipe recipe = rdao.findById(recipeId).orElse(null);
	    if (recipe != null) {
	    	//System.out.println("recipe Ingredients are:"+recipe.getRecipeIngredients());
	        return recipe.getRecipeIngredients();
	    } else {
	        return null;
	    }
	}
}