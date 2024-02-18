package com.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
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
import org.springframework.web.multipart.MultipartFile;
import com.demo.models.Ingredient;
import com.demo.models.Recipe;
import com.demo.models.Tag;
import com.demo.service.RecipeService;


@RestController
@CrossOrigin("*")
public class RecipeController {
	@Autowired
	RecipeService rservice;
	
	@GetMapping("/recipes")
	public ResponseEntity<List<Recipe>> getallRecipes(){
		List<Recipe> rlist=rservice.getAllRecipes();
		return ResponseEntity.ok(rlist);	
	}
	
	@GetMapping("/recipe/{Recipeid}")
	public ResponseEntity<Recipe> getById(@PathVariable int Recipeid){
		Recipe r=rservice.getRecipeById(Recipeid);
		if (r!=null)
			return ResponseEntity.ok(r);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
//	@PostMapping("/recipe")
//	public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe,@RequestParam Integer userId){
//		Recipe r=rservice.addNewRecipe(recipe,userId);
//		if(r!=null)
//		return ResponseEntity.ok(r);
//		else
//			return ResponseEntity.badRequest().body(null);
//	}
	
	@PostMapping("/recipe")
    public ResponseEntity<?> addRecipe(@RequestParam("recipeName") String recipeName,
                                       @RequestParam("instructions") String instructions,
                                       @RequestParam("cookTime") String cookTime,
                                       @RequestParam("totalCalories") Integer totalCalories,
                                       @RequestParam("recipeType") String recipeType,
                                       @RequestParam("recipeDescription") String recipeDescription,
                                       @RequestParam("recipeImage") MultipartFile recipeImage,
                                       @RequestParam Integer userId) {
        try {
            // Convert MultipartFile to byte array
            byte[] imageBytes = recipeImage.getBytes();
            
            // Create a new Recipe object
            Recipe newRecipe = new Recipe();
            newRecipe.setRecipeName(recipeName);
            newRecipe.setInstructions(instructions);
            newRecipe.setCookTime(cookTime);
            newRecipe.setTotalCalories(totalCalories);
            newRecipe.setRecipeType(recipeType);
            newRecipe.setRecipeDescription(recipeDescription);
            newRecipe.setRecipe_image(imageBytes); // Set the image bytes
            Recipe savedRecipe = rservice.addNewRecipe(newRecipe,userId);
            
            return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.BAD_REQUEST);
        }
    }
	
	@PutMapping("/recipe/{Recipeid}")
	public ResponseEntity<String> updateRecipe(@RequestBody Recipe r){
		Boolean status=rservice.updateRecipeById(r);
		if(status)
			return ResponseEntity.ok("Recipe updated successfully");	
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
//	@PutMapping("/recipe/{Recipeid}")
//    public ResponseEntity<?> updateRecipe(
//    		@RequestParam("recipeName") String recipeName,
//                                       @RequestParam("instructions") String instructions,
//                                       @RequestParam("cookTime") String cookTime,
//                                       @RequestParam("totalCalories") Integer totalCalories,
//                                       @RequestParam("recipeType") String recipeType,
//                                       @RequestParam("recipeDescription") String recipeDescription,
//                                       @RequestParam("recipeImage") MultipartFile recipeImage
//                                       ) {
//        try {
//            // Convert MultipartFile to byte array
//            byte[] imageBytes = recipeImage.getBytes();
//            
//            // Create a new Recipe object
//            Recipe newRecipe = new Recipe();
//            newRecipe.setRecipeName(recipeName);
//            newRecipe.setInstructions(instructions);
//            newRecipe.setCookTime(cookTime);
//            newRecipe.setTotalCalories(totalCalories);
//            newRecipe.setRecipeType(recipeType);
//            newRecipe.setRecipeDescription(recipeDescription);
//            newRecipe.setRecipe_image(imageBytes); // Set the image bytes
//            Boolean status = rservice.updateRecipeById(newRecipe);
//            
//            return new ResponseEntity<>(status, HttpStatus.CREATED);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to upload image", HttpStatus.BAD_REQUEST);
//        }
//    }
	
	@DeleteMapping("/recipe/{Recipeid}")
	public ResponseEntity<String> DeleteRecipe(@PathVariable int Recipeid){
		Boolean status=rservice.deleteRecipeById(Recipeid);
		if(status)
			return ResponseEntity.ok("Recipe deleted successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	
//	@GetMapping("/recipe/{recipeId}/users")
//	public ResponseEntity<Set<User>> getUsersByRecipe(@PathVariable int recipeId) {
//	    Set<User> users = rservice.getUsersByRecipe(recipeId);
//	    if (users != null)
//	        return ResponseEntity.ok(users);
//	    else
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//	}

	@GetMapping("/recipe/{recipeId}/tags")
	public ResponseEntity<Set<Tag>> getTagsByRecipe(@PathVariable int recipeId) {
	    Set<Tag> tags = rservice.getTagsByRecipe(recipeId);
	    if (tags != null)
	        return ResponseEntity.ok(tags);
	    else
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/recipe/{recipeId}/ingredients")
	public ResponseEntity<List<Ingredient>> getIngredientsByRecipe(@PathVariable int recipeId) {
	    List<Ingredient> ingredients = rservice.getIngredientsByRecipe(recipeId);
	    if (ingredients != null)
	        return ResponseEntity.ok(ingredients);
	    else
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}