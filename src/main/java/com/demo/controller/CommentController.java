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
import org.springframework.web.bind.annotation.RestController;
import com.demo.models.Comment;
import com.demo.service.CommentService;


@RestController
@CrossOrigin("*")
public class CommentController {
	@Autowired
	CommentService cservice;
	
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getallComments(){
		List<Comment> clist=cservice.getAllComments();
		return ResponseEntity.ok(clist);	
	}
	
	@GetMapping("/comment/{cid}")
	public ResponseEntity<Comment> getById(@PathVariable String cid){
		Comment c=cservice.getCommentById(cid);
		if (c!=null)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping("/comment")
	public ResponseEntity<String> addNewComment(@RequestBody Comment c){
		Boolean status=cservice.addNewComment(c);
		if(status)
			return ResponseEntity.ok("Comment added successfully");
		else 
			return ResponseEntity.badRequest().body("Comment insertion unsuccessful: Comment ID already exists");
	}
	
	@PutMapping("/comment/{cid}")
	public ResponseEntity<String> updateComment(@RequestBody Comment c){
		Boolean status=cservice.updateCommentById(c);
		if(status)
		return ResponseEntity.ok("Comment updated successfully");
		else 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/comment/{cid}")
	public ResponseEntity<String> DeleteComment(@PathVariable String cid){
		Boolean status=cservice.deleteCommentById(cid);
		if(status)
		return ResponseEntity.ok("Comment deleted successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}