package com.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.FeedbackDao;
import com.demo.dao.UserDao;
import com.demo.models.Feedback;
import com.demo.models.User;


@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	 private FeedbackDao fdao;
	
	@Autowired
	private UserDao udao;

	@Override
	public List<Feedback> getAllFeedbacks() {
		return fdao.findAll();
	}

	@Override
    public Boolean addNewFeedback(String f, Integer userId) {
            // Retrieve the user by userId
            Optional<User> userOptional = udao.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Feedback feedback=new Feedback();
                feedback.setFeedback(f);      
                feedback.setUser(user);
                fdao.save(feedback); // Save the feedback
                return true;
            }
            return false;
    }

	@Override
	public Feedback getFeedbackById(Integer fid) {
		Optional<Feedback> op=fdao.findById(fid);
		 if(op.isPresent()) {
			 return op.get();
		 }
		 return null;
	}

	@Override
	public Boolean updateFeedbackById(Feedback f) {
		Optional<Feedback> op=fdao.findById(f.getFeedbackId());
		if(op.isPresent()) {
			Feedback f1=op.get();
			f1.setFeedback(f.getFeedback());
			if(fdao.save(f1)!=null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean deleteFeedbackById(Integer fid) {
		if (fdao.existsById(fid)) {
			fdao.deleteById(fid);
			return true;
		}
		return false;	
	}

	
//	public User getUserByFeedback(String feedbackId) {
//	    Feedback feedback = fdao.findById(feedbackId).orElse(null);
//	    if (feedback != null) {
//	        return feedback.getUser();
//	    } else {
//	        return null;
//	    }
//	}
	
}