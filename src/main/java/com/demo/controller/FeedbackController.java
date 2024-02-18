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
import com.demo.models.Feedback;
import com.demo.service.FeedbackService;


@RestController
@CrossOrigin("*")
public class FeedbackController {
	@Autowired
	FeedbackService fservice;
	
	@GetMapping("/feedbacks")
	public ResponseEntity<List<Feedback>> getallFeedbacks(){
		List<Feedback> flist=fservice.getAllFeedbacks();
		return ResponseEntity.ok(flist);	
	}
	
	@GetMapping("/feedback/{fid}")
	public ResponseEntity<Feedback> getById(@PathVariable Integer fid){
		Feedback f=fservice.getFeedbackById(fid);
		if (f!=null)
			return ResponseEntity.ok(f);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/feedback")
	public ResponseEntity<String> addNewFeedback(@RequestBody String feedback, @RequestParam Integer userId) {
	    Boolean status = fservice.addNewFeedback(feedback, userId);
	    if (status) {
	        return ResponseEntity.ok("Feedback added successfully");
	    } else {
	    	System.out.println("In feedback");
	        return ResponseEntity.badRequest().body("Feedback insertion unsuccessful: User not found");
	    }
	}
	
	@PutMapping("/feedback/{fid}")
	public ResponseEntity<String> updateFeedback(@RequestBody Feedback f){
		Boolean status=fservice.updateFeedbackById(f);
		if(status)
			return ResponseEntity.ok("Feedback updated successfully");
		else 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/feedback/{fid}")
	public ResponseEntity<String> DeleteFeedback(@PathVariable Integer fid){
		Boolean status=fservice.deleteFeedbackById(fid);
		if(status)
			return ResponseEntity.ok("Feedback deleted successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
//	@GetMapping("/feedback/{feedbackId}/user")
//	public ResponseEntity<User> getUserByFeedback(@PathVariable Integer feedbackId) {
//	    User user = fservice.getUserByFeedback(feedbackId);
//	    if (user != null)
//	        return ResponseEntity.ok(user);
//	    else
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//	}	
}