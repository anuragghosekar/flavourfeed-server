package com.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;
import com.demo.models.MealPlanning;
import com.demo.models.User;
import com.demo.service.MealPlanningService;


@RestController
@CrossOrigin("*")
public class MealPlanningController {
	@Autowired
	MealPlanningService mservice;
	
	@GetMapping("/mealplannings")
	public ResponseEntity<List<MealPlanning>> getallMealPlannings(){
		List<MealPlanning> mlist=mservice.getAllMealPlannings();
		return ResponseEntity.ok(mlist);	
	}
	
	@GetMapping("/mealplanning/{mpid}")
	public ResponseEntity<MealPlanning> getById(@PathVariable int mpid){
		MealPlanning m=mservice.getMealPlanningById(mpid);
		if (m!=null)
			return ResponseEntity.ok(m);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/mealplanning")
	public ResponseEntity<String> addNewMealPlanning(@RequestBody MealPlanning m){
		Boolean status=mservice.addNewMealPlanning(m);
		if(status)
			return ResponseEntity.ok("MealPlanning added successfully");
		else
			return ResponseEntity.badRequest().body("MealPlanning insertion unsuccessful: Meal ID already exists");
	}
	
	@PutMapping("/mealplanning/{mpid}")
	public ResponseEntity<String> updateMealPlanning(@RequestBody MealPlanning m){
		Boolean status=mservice.updateMealPlanningById(m);
		if(status)
		return ResponseEntity.ok("MealPlanning updated successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	
	@DeleteMapping("/mealplanning/{mpid}")
	public ResponseEntity<String> DeleteMealPlanning(@PathVariable int mpid){
		Boolean status=mservice.deleteMealPlanningById(mpid);
		if(status)
			return ResponseEntity.ok("MealPlanning deleted successfully");
		else 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@GetMapping("/mealplanning/{mealPlanningId}/users")
	public ResponseEntity<Set<User>> getUsersByMealPlanning(@PathVariable int mealPlanningId) {
	    Set<User> users = mservice.getUsersByMealPlanning(mealPlanningId);
	    if (users != null)
	        return ResponseEntity.ok(users);
	    else
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}