package com.demo.Authorization;

import org.springframework.stereotype.Component;

@Component
public class AuthStatus {
	
	private Boolean isAuthenticated;

	public Boolean getIsAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}