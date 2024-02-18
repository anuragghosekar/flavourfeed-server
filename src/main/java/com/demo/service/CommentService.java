package com.demo.service;

import java.util.List;
import com.demo.models.Comment;
import com.demo.models.User;

public interface CommentService {

	List<Comment> getAllComments();

	Boolean addNewComment(Comment c);

	Comment getCommentById(String cid);

	Boolean updateCommentById(Comment c);

	Boolean deleteCommentById(String cid);
	
	User getUserByComment(String commentid);
	
}