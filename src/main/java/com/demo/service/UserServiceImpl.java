package com.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.demo.dao.UserDao;
import com.demo.models.Comment;
import com.demo.models.Feedback;
import com.demo.models.Recipe;
import com.demo.models.User;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	 private UserDao udao;

	public List<User> getAllUsers() {
		return udao.findAll();
	}
	
	public Boolean updatePassword(@RequestBody User u) {
	    User user = udao.findByUsername(u.getUsername());
	    if (user != null) {
	        user.setPassword(u.getPassword());
	        udao.save(user);
	        return true;
	    } else {
	    	System.out.println("In false");
	        return false;
	    }
	}

	public User login(String username, String password) {
	    User user = udao.findByUsername(username);
	    if (user != null && user.getPassword().equals(password)) {
	        return user;
	    } else {
	        return null;
	    }
	}
	
	@Override
	public Boolean deleteUserByUsernameAndPassword(String username, String password) {
	    User user = udao.findByUsernameAndPassword(username, password);
	    if (user != null) {
	        udao.delete(user);
	        return true;
	    } else {
	        return false;
	    }
	}

	@Override
	public Boolean deleteUserById(int uid) {
		if (udao.existsById(uid)) {
			udao.deleteById(uid);
			return true;
		}
		return false;	
	}

	@Override
	public Boolean updateUserById(User u) {
	    Optional<User> op = udao.findById(u.getUserId());
	    if (op.isPresent()) {
	        User u1 = op.get();
	        u1.setFirstname(u.getFirstname());
	        u1.setLastname(u.getLastname());
	        u1.setUsername(u.getUsername());
	        u1.setPassword(u.getPassword());
	        u1.setEmail(u.getEmail());
	        u1.setRole(u.getRole());
	        u1.setGender(u.getGender()); 
	        u1.setPreferences(u.getPreferences()); 
	        u1.setAllergies(u.getAllergies()); 
	        u1.setUserRecipes(u.getUserRecipes());
	        u1.setUserFeedbacks(u.getUserFeedbacks());
	        u1.setUserComments(u.getUserComments());
	        if (udao.save(u1) != null) {
	            return true;
	        }
	    }
	    return false;
	}
	
	@Override
	public Boolean addNewUser(User u) {
	    try {
	    	u.setRole("user");
	        udao.save(u);
	        return true;
	    } catch (Exception e){
	        return false;
	    }
	}

	@Override
	public User getUserById(int uid) {
		Optional<User> op=udao.findById(uid);
		 if(op.isPresent()) {
			 return op.get();
		 }
		 return null;
	}

	@Override
	public List<Recipe> getUserRecipes(int userId) {
	    User user = udao.findById(userId).orElse(null);
	    if (user != null) {
	        List<Recipe> userRecipes = user.getUserRecipes();
	        return userRecipes;
	    } else {
	        return null;
	    }
	}


	@Override
	public List<Feedback> getUserFeedbacks(int userId) {
		    User user = udao.findById(userId).orElse(null);
		    if (user != null) {
		        return user.getUserFeedbacks();
		    } else {
		        return null;
		    }
	}

	@Override
	public List<Comment> getUserComments(int userId) {
	    User user = udao.findById(userId).orElse(null);
	    if (user != null) {
	        return user.getUserComments();
	    } else {
	        return null;
	    }
	}

	@Override
	public String[] getUserAllergies(int userId) {
	    User user = udao.findById(userId).orElse(null);
	    if (user != null && user.getAllergies() != null) {
	        String allergiesString = user.getAllergies();
	        String[] allergiesArray = allergiesString.split(",");
	        return allergiesArray;
	    }
	    return null;
	}
	
	@Override
	public String[] getUserPreferences(int userId) {
	    User user = udao.findById(userId).orElse(null);
	    if (user != null && user.getPreferences() != null) {
	        String preferencesString = String.valueOf(user.getPreferences());
	        String[] preferencesArray = preferencesString.split(",");
	        return preferencesArray;
	    }
	    return null;
	}
}