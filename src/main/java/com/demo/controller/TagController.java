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
import com.demo.models.Recipe;
import com.demo.models.Tag;
import com.demo.service.TagService;


@RestController
@CrossOrigin("*")
public class TagController {
	@Autowired
	TagService tservice;
	
	@GetMapping("/tags")
	public ResponseEntity<List<Tag>> getallTags(){
		List<Tag> tlist=tservice.getAllTags();
		return ResponseEntity.ok(tlist);	
	}
	
	@GetMapping("/tag/{tagid}")
	public ResponseEntity<Tag> getById(@PathVariable int tagid){
		Tag t=tservice.getTagById(tagid);
		if (t!=null)
			return ResponseEntity.ok(t);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/tag")
	public ResponseEntity<String> addNewTag(@RequestBody Tag t){
		Boolean status=tservice.addNewTag(t);
		if(status)
			return ResponseEntity.ok("Tag added successfully");
		else
			return ResponseEntity.badRequest().body("Tag insertion unsuccessful: Tag ID already exists");
	}
	
	@PutMapping("/tag/{tagid}")
	public ResponseEntity<String> updateTag(@RequestBody Tag t){
		Boolean status=tservice.updateTagById(t);
		if(status)
			return ResponseEntity.ok("Tag updated successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/tag/{tagid}")
	public ResponseEntity<String> DeleteTag(@PathVariable int tagid){
		Boolean status=tservice.deleteTagById(tagid);
		if(status)
			return ResponseEntity.ok("Tag deleted successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/tag/{tagId}/recipes")
	public ResponseEntity<Set<Recipe>> getRecipesByTag(@PathVariable int tagId) {
	    Set<Recipe> recipes = tservice.getRecipesByTag(tagId);
	    if (recipes != null)
	        return ResponseEntity.ok(recipes);
	    else
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}