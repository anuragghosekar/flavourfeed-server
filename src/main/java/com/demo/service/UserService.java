package com.demo.service;
import java.util.List;
import com.demo.models.Comment;
import com.demo.models.Feedback;
import com.demo.models.Recipe;
import com.demo.models.User;

public interface UserService {

	List<User> getAllUsers();

	Boolean addNewUser(User u);

	User getUserById(int pid);

	Boolean updateUserById(User u);

	Boolean deleteUserById(int id);

	List<Recipe> getUserRecipes(int userId);

	List<Feedback> getUserFeedbacks(int userId);

	List<Comment> getUserComments(int userId);

	Boolean updatePassword(User u);

	User login(String username, String password);

	Boolean deleteUserByUsernameAndPassword(String username, String password);

	String[] getUserAllergies(int userId);

	String[] getUserPreferences(int userId);

}