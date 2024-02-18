package com.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.CommentDao;
import com.demo.models.Comment;
import com.demo.models.User;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	 private CommentDao cdao;
 
	@Override
	public List<Comment> getAllComments() {
		return cdao.findAll();
	}

	@Override
	public Boolean addNewComment(Comment c) {
		try {
	        cdao.save(c);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}

	@Override
	public Comment getCommentById(String cid) {
		Optional<Comment> op=cdao.findById(cid);
		 if(op.isPresent()) {
			 return op.get();
		 }
		 return null;
	}

	@Override
	public Boolean updateCommentById(Comment c) {
		Optional<Comment> op=cdao.findById(c.getCommentId());
		if(op.isPresent()) {
			Comment c1=op.get();
			c1.setComment(c.getComment());
			if(cdao.save(c1)!=null) {
				return true;
			}
		}
		return false;	
	}

	@Override
	public Boolean deleteCommentById(String cid) {
		 if (cdao.existsById(cid)) {
			cdao.deleteById(cid);
			return true;
		}
		return false;	
	}
	
	public User getUserByComment(String CommentId) {
	    Comment comment = cdao.findById(CommentId).orElse(null);
	    if (comment != null) {
	        return comment.getUser();
	    } else {
	        return null;
	    }
	}
}