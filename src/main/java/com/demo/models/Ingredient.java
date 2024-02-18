package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore; 

@Entity
@Table(name = "tbl_ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Integer ingredientId;

    @NotBlank(message = "Ingredient name cannot be Blank")
    @Column(name = "ingredient_name")
    private String ingredientName;

    @Column(name = "ingredient_type")
    private String ingredientType;
    
    @Column(name = "ingredient_description")
    private String ingredientDescription;

    @Column(name = "calorie_count")
    private Integer calorieCount;

    @JsonIgnore
    @OneToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

	public Ingredient() {
		super();
	}
	
	public Ingredient(Integer ingredientId,
			@NotBlank(message = "Ingredient name cannot be Blank") String ingredientName, String ingredientType,
			String ingredientDescription, Integer calorieCount, Recipe r) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.ingredientType = ingredientType;
		this.ingredientDescription = ingredientDescription;
		this.calorieCount = calorieCount;
		this.recipe = r;
	}

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getIngredientType() {
		return ingredientType;
	}

	public void setIngredientType(String ingredientType) {
		this.ingredientType = ingredientType;
	}

	public String getIngredientDescription() {
		return ingredientDescription;
	}

	public void setIngredientDescription(String ingredientDescription) {
		this.ingredientDescription = ingredientDescription;
	}

	public Integer getCalorieCount() {
		return calorieCount;
	}

	public void setCalorieCount(Integer calorieCount) {
		this.calorieCount = calorieCount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe r) {
		this.recipe = r;
	}
	
	
	@Override
	public boolean equals(Object obj) {
        return (this.ingredientName == ((Ingredient)obj).ingredientName);
}

//	@Override
//	public String toString() {
//		return "Ingredient [ingredientId=" + ingredientId + ", ingredientName=" + ingredientName + ", ingredientType="
//				+ ingredientType + ", ingredientDescription=" + ingredientDescription + ", calorieCount=" + calorieCount
//				+ ", recipe=" + recipe + "]";
//	}
}