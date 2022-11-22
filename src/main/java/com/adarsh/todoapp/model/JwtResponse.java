package com.adarsh.todoapp.model;

public class JwtResponse {

	private UserModel user;
	private String jwtToken;
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(UserModel user, String jwtToken) {
		super();
		this.user = user;
		this.jwtToken = jwtToken;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
}
