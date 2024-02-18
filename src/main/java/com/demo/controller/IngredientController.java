package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.models.Ingredient;
import com.demo.service.IngredientService;


@RestController
@CrossOrigin("*")
public class IngredientController {
	@Autowired
	IngredientService iservice;
	
	@GetMapping("/ingredients")
	public ResponseEntity<List<Ingredient>> getallIngredients(){
		List<Ingredient> ilist=iservice.getAllIngredients();
		return ResponseEntity.ok(ilist);	
	}
	
	@GetMapping("/ingredient/{ingid}")
	public ResponseEntity<Ingredient> getById(@PathVariable int ingid){
		Ingredient i=iservice.getIngredientById(ingid);
		if (i!=null)
			return ResponseEntity.ok(i);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/ingredient")
	public ResponseEntity<String> addNewIngredient(@RequestBody Ingredient i,@RequestParam Integer recipeId){
		System.out.println(recipeId);
		Boolean status=iservice.addNewIngredient(i,recipeId);
		if(status) {
			return ResponseEntity.ok("Ingredient added successfully");
		}
		else {
		return ResponseEntity.badRequest().body("Ingredient insertion unsuccessful: Ingredient ID already exists");
		}
	}
	
	@PutMapping("/ingredient/{ingid}")
	public ResponseEntity<String> updateIngredient(@RequestBody Ingredient i){
		Boolean status=iservice.updateIngredientById(i);
		if(status)
			return ResponseEntity.ok("Ingredient updated successfully");
	    else 
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
	@GetMapping("/distinctingredients")
    public ResponseEntity<?> getAllDistinctIngredients() {
        try {
        	System.out.println("in Ingredient try");
            List<Ingredient> distinctIngredients = iservice.getAllDistinctIngredients();
            return new ResponseEntity<>(distinctIngredients, HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println("in Ingredient catch");
            return new ResponseEntity<>("Failed to retrieve distinct ingredients", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@DeleteMapping("/ingredient/{ingid}")
	public ResponseEntity<String> DeleteIngredient(@PathVariable int ingid){
		Boolean status=iservice.deleteIngredientById(ingid);
		if(status)
			return ResponseEntity.ok("Ingredient deleted successfully");
	    else
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
//	@GetMapping("/ingredient/{ingredientId}/recipes")
//	public ResponseEntity<Set<Recipe>> getRecipesByIngredient(@PathVariable int ingredientId) {
//	    Set<Recipe> recipes = iservice.getRecipesByIngredient(ingredientId);
//	    if (recipes != null)
//	        return ResponseEntity.ok(recipes);
//	    else
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//	}
}