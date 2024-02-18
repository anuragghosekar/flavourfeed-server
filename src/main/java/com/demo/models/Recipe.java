package com.demo.models;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude; 

@Entity
@Table(name = "tbl_recipe")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Integer recipeId;

    @NotBlank(message = "Recipe name cannot be Blank")
    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "cook_time")
    private String cookTime;

    @Column(name = "total_calories")
    private Integer totalCalories;

    @Column(name = "recipe_type")
    private String recipeType;
    
    @JsonIgnore
    @OneToOne
	@JoinColumn(name="user_id")
	private User user;

    @Column(name = "recipe_description")
    private String recipeDescription;

    @Lob
    @Column(name = "recipe_image", nullable = true)
    private byte[] recipe_image;

    
    @OneToMany
    private Set<Tag> recipeTags;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredient> recipeIngredients;

    public Recipe() {
        super();
    }
    
	public Recipe(Integer recipeId, @NotBlank(message = "Recipe name cannot be Blank") String recipeName,
			String instructions, String cookTime, Integer totalCalories, String recipeType, String recipeDescription,
			User u, List<User> userRecipes, Set<Tag> recipeTags, List<Ingredient> recipeIngredients) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.instructions = instructions;
		this.cookTime = cookTime;
		this.totalCalories = totalCalories;
		this.recipeType = recipeType;
		this.recipeDescription = recipeDescription;
		this.recipeTags = recipeTags;
		this.recipeIngredients = recipeIngredients;
	}
	
	public Recipe(Integer recipeId, @NotBlank(message = "Recipe name cannot be Blank") String recipeName,
			String instructions, String cookTime, Integer totalCalories, String recipeType, String recipeDescription,
			byte[] recipe_image, User u, List<User> userRecipes, Set<Tag> recipeTags,
			List<Ingredient> recipeIngredients) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.instructions = instructions;
		this.cookTime = cookTime;
		this.totalCalories = totalCalories;
		this.recipeType = recipeType;
		this.recipeDescription = recipeDescription;
		this.recipe_image = recipe_image;
		this.recipeTags = recipeTags;
		this.recipeIngredients = recipeIngredients;
	}

	public byte[] getRecipe_image() {
		return recipe_image;
	}

	public void setRecipe_image(byte[] recipe_image) {
		this.recipe_image = recipe_image;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getCookTime() {
		return cookTime;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(Integer totalCalories) {
		this.totalCalories = totalCalories;
	}

	public Set<Tag> getRecipeTags() {
		return recipeTags;
	}

	public void setRecipeTags(Set<Tag> recipeTags) {
		this.recipeTags = recipeTags;
	}

	public List<Ingredient> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setRecipeIngredients(List<Ingredient> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}
	
	public String getRecipeType() {
		return recipeType;
	}

	public void setRecipeType(String recipeType) {
		this.recipeType = recipeType;
	}

	public String getRecipeDescription() {
		return recipeDescription;
	}

	public void setRecipeDescription(String recipeDescription) {
		this.recipeDescription = recipeDescription;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=" + recipeName + ", instructions=" + instructions
				+ ", cookTime=" + cookTime + ", totalCalories=" + totalCalories + ", recipeType=" + recipeType
				+ ", recipeDescription=" + recipeDescription + ", recipe_image=" + Arrays.toString(recipe_image)
				+ ", recipeTags=" + recipeTags + ", recipeIngredients=" + recipeIngredients + "]";
	}
}