package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.query.Procedure;
import com.demo.models.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
}                 