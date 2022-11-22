package com.adarsh.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.todoapp.model.UserModel;
import com.adarsh.todoapp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	

	@PostMapping("/create")
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
		UserModel userResp=userService.createUser(user);
		return ResponseEntity.ok(userResp);
	}

}
