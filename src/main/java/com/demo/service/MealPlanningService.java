package com.demo.service;

import java.util.List;
import java.util.Set;
import com.demo.models.MealPlanning;
import com.demo.models.User;

public interface MealPlanningService {

	List<MealPlanning> getAllMealPlannings();

	Boolean addNewMealPlanning(MealPlanning mp);

	MealPlanning getMealPlanningById(int mid);

	Boolean updateMealPlanningById(MealPlanning m);

	Boolean deleteMealPlanningById(int mpid);

	Set<User> getUsersByMealPlanning(int mealPlanningId);

}