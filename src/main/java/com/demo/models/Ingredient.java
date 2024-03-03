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

    @Column(name = "quantity")
    private String quantity;

    @JsonIgnore
    @OneToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

	public Ingredient() {
		super();
	}
	
	public Ingredient(Integer ingredientId,
			@NotBlank(message = "Ingredient name cannot be Blank") String ingredientName,String quantity, Recipe r) {
		super();
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.quantity = quantity;
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

	

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

}