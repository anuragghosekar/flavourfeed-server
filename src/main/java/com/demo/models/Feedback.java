package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
//import com.fasterxml.jackson.annotation.JsonIgnore; 

@Entity
@Table(name = "tbl_feedback")
public class Feedback {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;
    
    @NotBlank(message = "Feedback cannot be Blank")
    @Column(name = "feedback")
    private String feedback;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id")
    private User user;

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", feedback=" + feedback + ", user=" + user + "]";
	}

	public Feedback(Integer feedbackId, @NotBlank(message = "Feedback cannot be Blank") String feedback, User user) {
		super();
		this.feedbackId = feedbackId;
		this.feedback = feedback;
		this.user = user;
	}

	public Feedback() {
		super();
	}   
}