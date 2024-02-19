package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
 

@Entity
@Table(name = "tbl_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank(message = "Firstname is required")
    @Column(name = "first_name")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Column(name = "last_name")
    private String lastname;

    @NotBlank(message = "Username is required")
    @Column(name = "username", unique = true)
    private String username;

//    @NotBlank(message = "Password is required")
//    @Size(min = 8, message = "Password must be at least 8 characters Long")
    @Column(name = "password")
    private String password;

    @Email
    @NotBlank(message = "Email is required")
    @Column(name = "email_id")
    private String email;

    @Column(name = "date_of_birth")
    private String DateOfBirth;

    @Column(name = "role")
    private String role = "admin";

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phonenumber;

    @Column(name = "preferences")
    private String preferences;

    @Column(name = "allergies")
    private String allergies;

    @Lob
    @Column(name = "user_image", nullable = true)
    private byte[] user_image;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recipe> userRecipes;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> userFeedbacks;

    @ManyToOne
    private MealPlanning m;

    @JsonIgnore
    @OneToMany
    private List<Comment> userComments;
    

    public User() {
    	super();
    	userRecipes=new ArrayList<Recipe>();
    	userRecipes=new ArrayList<Recipe>();
    	userRecipes=new ArrayList<Recipe>();
        
    }
     
	public User(Integer userId, @NotBlank(message = "Firstname is required") String firstname,
			@NotBlank(message = "Lastname is required") String lastname,
			@NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters Long") String password,
			@Email @NotBlank(message = "Email is required") String email, String DateOfBirth, String role, String gender, String phonenumber, String preferences, String allergies,
			List<Recipe> userRecipes, List<Feedback> userFeedbacks, MealPlanning m, List<Comment> userComments) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.DateOfBirth = DateOfBirth;
		this.role = role;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.preferences = preferences;
		this.allergies = allergies;
		this.userRecipes = userRecipes;
		this.userFeedbacks = userFeedbacks;
		this.m = m;
		this.userComments = userComments;
	}
	
	public User(Integer userId, @NotBlank(message = "Firstname is required") String firstname,
			@NotBlank(message = "Lastname is required") String lastname,
			@NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters Long") String password,
			@Email @NotBlank(message = "Email is required") String email, String DateOfBirth, String role, String gender, String phonenumber, String preferences, String allergies, byte[] user_image,
			List<Recipe> userRecipes, List<Feedback> userFeedbacks, MealPlanning m, List<Comment> userComments) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.DateOfBirth = DateOfBirth;
		this.role = role;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.preferences = preferences;
		this.allergies = allergies;
		this.user_image = user_image;
		this.userRecipes = userRecipes;
		this.userFeedbacks = userFeedbacks;
		this.m = m;
		this.userComments = userComments;
	}

	public User(Integer userId) {
		this.userId=userId;
	}

	public byte[] getImages() {
        return user_image;
    }
	
	public void setImages(byte[] images) {
        this.user_image = images;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Recipe> getUserRecipes() {
		return userRecipes;
	}

	public void setUserRecipes(List<Recipe> userRecipes) {
		this.userRecipes = userRecipes;
	}

	public List<Feedback> getUserFeedbacks() {
		return userFeedbacks;
	}

	public void setUserFeedbacks(List<Feedback> userFeedbacks) {
		this.userFeedbacks = userFeedbacks;
	}

	public List<Comment> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<Comment> userComments) {
		this.userComments = userComments;
	}
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public MealPlanning getM() {
		return m;
	}

	public void setM(MealPlanning m) {
		this.m = m;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String DateOfBirth) {
		this.DateOfBirth = DateOfBirth;
	}

	public Object getImageBase64() {
		if (this.user_image != null) {
            return Base64.getEncoder().encodeToString(this.user_image);
        }
        return null;
	}

//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
//				+ username + ", password=" + password + ", email=" + email + ", DateOfBirth=" + DateOfBirth + ", role="
//				+ role + ", address=" + address + ", gender=" + gender + ", phonenumber=" + phonenumber
//				+ ", preferences=" + preferences + ", allergies=" + allergies + ", user_image="
//				+ Arrays.toString(user_image) + ", userRecipes=" + userRecipes + ", userFeedbacks=" + userFeedbacks
//				+ ", m=" + m + ", userComments=" + userComments + "]";
//	}	
}