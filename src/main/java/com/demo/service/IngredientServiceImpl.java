package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.IngredientDao;
import com.demo.dao.RecipeDao;
import com.demo.models.Ingredient;
import com.demo.models.Recipe;


@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientDao idao;
	@Autowired
	private RecipeDao rdao;
	
	List<Ingredient>ingredientlist=new ArrayList<Ingredient>();;

	@Override
	public List<Ingredient> getAllIngredients() {
		return idao.findAll();
	}

	@Override
	public Boolean addNewIngredient(Ingredient i,Integer recipeId) {
		try {
	    	Recipe r = rdao.findById(recipeId).orElse(null);
	    	Ingredient ingredient=new Ingredient();
	    	ingredient.setIngredientName(i.getIngredientName());
	    	ingredient.setCalorieCount(i.getCalorieCount());
	    	ingredient.setIngredientDescription(i.getIngredientDescription());
	    	ingredient.setIngredientType(i.getIngredientType());
	    	i.setRecipe(r);
	        idao.save(i);
	        ingredientlist.add(ingredient);
	        r.setRecipeIngredients(ingredientlist);
	       // r.getRecipeIngredients().add(ingredient);
	        return true;
	    } catch (Exception e) {
	    	System.out.println("Error Service Add New Ingredient");
	        return false;
	    }
	}

	@Override
	public Ingredient getIngredientById(int ingid) {
		Optional<Ingredient> op=idao.findById(ingid);
		 if(op.isPresent()) {
			 return op.get();
		 }
		 return null;
	}

	@Override
	public Boolean updateIngredientById(Ingredient i) {
		Optional<Ingredient> op=idao.findById(i.getIngredientId());
		if(op.isPresent()) {
			Ingredient i1=op.get();
			i1.setIngredientName(i.getIngredientName());
			i1.setIngredientType(i.getIngredientType());
			i1.setCalorieCount(i.getCalorieCount());
			if(idao.save(i1)!=null) {
				return true;
			};
		}
		return false;
	}

	@Override
	public Boolean deleteIngredientById(int ingid) {
		if (idao.existsById(ingid)) {
			idao.deleteById(ingid);
			return true;
		}
		return false;
	}

	@Override
    @Transactional
    public Boolean addAllIngredients(List<Ingredient> recipeIngredients, Recipe r) {
        try {
        	for(Ingredient i:recipeIngredients) {
        	}
            for (Ingredient ingredient : recipeIngredients) {
                ingredient.setRecipe(r);
            }
            idao.saveAll(recipeIngredients);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public List<Ingredient> getAllDistinctIngredients() {
		return idao.findAllDistinctIngredientNames();
	}
}