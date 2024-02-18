package com.demo.models;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "tbl_meal_planning")
public class MealPlanning {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
	private Integer mealId;
	
	@Column(name = "meal_time")
	private String mealTime;
	
	@Column(name = "total_calories")
	private Integer totalCalories;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@OneToMany
	private Set<User> userMeals;
	
	public MealPlanning() {
		super();
	}
	
	public MealPlanning(Integer mealId, String mealTime, Integer totalCalories, Integer quantity,
			Set<User> userMeals) {
		super();
		this.mealId = mealId;
		this.mealTime = mealTime;
		this.totalCalories = totalCalories;
		this.quantity = quantity;
		this.userMeals = userMeals;
	}
	
	public Integer getMealId() {
		return mealId;
	}
	
	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}
	
	public String getMealTime() {
		return mealTime;
	}
	
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}
	
	public Integer getTotalCalories() {
		return totalCalories;
	}
	
	public void setTotalCalories(Integer totalCalories) {
		this.totalCalories = totalCalories;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Set<User> getUserMeals() {
		return userMeals;
	}
	
	public void setUserMeals(Set<User> userMeals) {
		this.userMeals = userMeals;
	}
	
	@Override
	public String toString() {
		return "MealPlanning [mealId=" + mealId + ", mealTime=" + mealTime + ", totalCalories=" + totalCalories
				+ ", quantity=" + quantity + ", userMeals=" + userMeals + "]";
	}						    
}