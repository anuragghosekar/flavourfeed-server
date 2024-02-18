package com.demo.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {
	@Autowired
	AuthStatus authstatus;

	@GetMapping("/user/isAuthenticated")
	public ResponseEntity<Boolean> isUserAuthenticated(){
		return ResponseEntity.ok(authstatus.getIsAuthenticated());
	}
}