package com.demo.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import com.demo.Authorization.AuthStatus;
import com.demo.models.Comment;
import com.demo.models.Feedback;
import com.demo.models.Recipe;
import com.demo.models.User;
import com.demo.service.UserService;


@RestController
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserService uservice;
	
	@Autowired
    private AuthStatus authStatus;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getallusers(){
		List<User> ulist=uservice.getAllUsers();
		return ResponseEntity.ok(ulist);
	}

	
	@PutMapping("/user/{username}/updatepassword")
	public ResponseEntity<String> updatePassword(@RequestBody User u) {
	    boolean passwordUpdated = uservice.updatePassword(u);
	    if (passwordUpdated)
	        return ResponseEntity.ok("Password updated successfully");
	    else
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or password not updated");
	}
	
	@GetMapping("/logout")
	    public ResponseEntity<Boolean> logout() {
		 authStatus.setIsAuthenticated(false);
		 return ResponseEntity.ok(authStatus.getIsAuthenticated());
	    }

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
	    User u = uservice.login(user.getUsername(), user.getPassword());
	    if (u != null) {
	    	authStatus.setIsAuthenticated(true);
	    	return ResponseEntity.ok(u);
	    }  
	    else 
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<User> getById(@PathVariable int userid){
		User u=uservice.getUserById(userid);
		if (u!=null)
			return ResponseEntity.ok(u);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
//	@PostMapping("/user")
//	public ResponseEntity<String> addNewUser(@RequestBody User u){
//		Boolean status=uservice.addNewUser(u);
//		if(status)
//			return ResponseEntity.ok("User added successfully");
//		else
//			return ResponseEntity.badRequest().body("User insertion unsuccessful: User ID or username already exists");
//	}
	
	@PostMapping("/user")
    public ResponseEntity<String> addNewUser(@RequestParam("image") MultipartFile image,
                                             @RequestParam("firstname") String firstname,
                                             @RequestParam("lastname") String lastname,
                                             @RequestParam("username") String username,
                                             @RequestParam("password") String password,
                                             @RequestParam("email") String email,
                                             @RequestParam("phonenumber") String phonenumber,
                                             @RequestParam("gender") String gender,
//                                             @RequestParam("address") String address,
                                             @RequestParam("preferences") String preferences,
                                             @RequestParam("allergies") String allergies,
                                             @RequestParam("dateOfBirth") String dateOfBirth) {
        try {
            byte[] imageBytes = image.getBytes();
            
            User user = new User();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhonenumber(phonenumber);
            user.setGender(gender);
//            user.setAddress(address);
            user.setPreferences(preferences);
            user.setAllergies(allergies);
            user.setDateOfBirth(dateOfBirth);
            user.setImages(imageBytes); // Set image byte array
            
            uservice.addNewUser(user);
            
            return ResponseEntity.ok("User added successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }
	
//	@PutMapping("/user/{userid}")
//	public ResponseEntity<String> updateUser(@RequestBody User u){
//		Boolean status=uservice.updateUserById(u);
//		if(status)
//			return ResponseEntity.ok("User Updated successfully");
//		else
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//	}
	
    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateProfile(@PathVariable Integer userId, @RequestParam("profileImage") MultipartFile profileImage,
                                           @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
                                           @RequestParam("username") String username, @RequestParam("password") String password,
                                           @RequestParam("email") String email, @RequestParam("dateOfBirth") String dateOfBirth,
                                           @RequestParam("address") String address, @RequestParam("gender") String gender,
                                           @RequestParam("phonenumber") String phonenumber, @RequestParam("preferences") String preferences,
                                           @RequestParam("allergies") String allergies) {
        try {
            User user = uservice.getUserById(userId);

            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            byte[] imageBytes = profileImage.getBytes();

            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setDateOfBirth(dateOfBirth);
//            user.setAddress(address);
            user.setGender(gender);
            user.setPhonenumber(phonenumber);
            user.setPreferences(preferences);
            user.setAllergies(allergies);
            user.setImages(imageBytes);

            uservice.updateUserById(user);

            return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error updating profile", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("/admin/{userId}")
    public ResponseEntity<?> updateadminProfile(@PathVariable Integer userId, @RequestParam("profileImage") MultipartFile profileImage,
                                           @RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
                                           @RequestParam("username") String username, @RequestParam("password") String password,
                                           @RequestParam("email") String email, @RequestParam("dateOfBirth") String dateOfBirth,
                                           @RequestParam("address") String address, @RequestParam("role")String role,@RequestParam("gender") String gender,
                                           @RequestParam("phonenumber") String phonenumber, @RequestParam("preferences") String preferences,
                                           @RequestParam("allergies") String allergies) {
        try {
            User user = uservice.getUserById(userId);

            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

            byte[] imageBytes = profileImage.getBytes();

            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(role);
            user.setEmail(email);
            user.setDateOfBirth(dateOfBirth);
//            user.setAddress(address);
            user.setGender(gender);
            user.setPhonenumber(phonenumber);
            user.setPreferences(preferences);
            user.setAllergies(allergies);
            user.setImages(imageBytes);

            uservice.updateUserById(user);

            return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error updating profile", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@DeleteMapping("/user/{userid}")
	public ResponseEntity<String> DeleteUser(@PathVariable int userid){
		Boolean status=uservice.deleteUserById(userid);
		if(status)
			return ResponseEntity.ok("User Deleted successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/user/{userId}/recipes")
	public List<Recipe> getUserRecipes(@PathVariable int userId) {
		try {
	    List<Recipe> recipes = uservice.getUserRecipes(userId);
	    return recipes;
		}catch(Exception e) {
			return null;
		}    
	}
	
	@GetMapping("/user/{userId}/feedbacks")
	public ResponseEntity<List<Feedback>> getUserFeedbacks(@PathVariable Integer userId) {
	    List<Feedback> feedbacks = uservice.getUserFeedbacks(userId);
	    if (feedbacks != null)
	        return ResponseEntity.ok(feedbacks);
	    else
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/user/{userId}/comments")
	public ResponseEntity<List<Comment>> getUserComments(@PathVariable int userId) {
	    List<Comment> comments = uservice.getUserComments(userId);
	    if (comments != null)
	        return ResponseEntity.ok(comments);
	    else
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/user/{userId}/allergies")
    public ResponseEntity<String[]> getUserAllergies(@PathVariable int userId) {
        String[] allergies = uservice.getUserAllergies(userId);
        if (allergies != null)
            return ResponseEntity.ok(allergies);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/user/{userId}/preferences")
    public ResponseEntity<String[]> getUserPreferences(@PathVariable int userId) {
        String[] preferences = uservice.getUserPreferences(userId);
        if (preferences != null)
            return ResponseEntity.ok(preferences);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}                                                                                     