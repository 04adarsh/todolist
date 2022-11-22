package com.adarsh.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.todoapp.model.JwtRequest;
import com.adarsh.todoapp.model.JwtResponse;
import com.adarsh.todoapp.service.JwtService;

@RestController
@CrossOrigin("*")
public class JwtController {
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/check")
	public String testing() {
		return "Test successful";
	}
	
	@PostMapping("/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) {
		JwtResponse jwtResponse=jwtService.createJwtToken(jwtRequest);
		return jwtResponse;
	}

}
