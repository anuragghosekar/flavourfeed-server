package com.demo.service;

import java.util.List;
import com.demo.models.Feedback;

public interface FeedbackService {

	List<Feedback> getAllFeedbacks();

	Boolean addNewFeedback(String f,Integer userId);

	Feedback getFeedbackById(Integer fid);

	Boolean updateFeedbackById(Feedback f);

	Boolean deleteFeedbackById(Integer fid);

	//User getUserByFeedback(String feedbackId);

}