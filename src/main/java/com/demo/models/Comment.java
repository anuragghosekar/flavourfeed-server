package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_comments")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private String commentId;

    @NotBlank(message = "Comment cannot be Blank")
    @Column(name = "comment")
    private String comment;
    
    @ManyToOne
    private User u;

	public Comment() {
		super();
	}

	public Comment(String commentId, String comment) {
		super();
		this.commentId = commentId;
		this.comment = comment;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public User getUser() {
		return u;
	}

	public void setUser(User u) {
		this.u = u;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", comment=" + comment + "]";
	}    
}