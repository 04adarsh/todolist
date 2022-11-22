package com.adarsh.todoapp.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.todoapp.model.Role;
import com.adarsh.todoapp.service.RoleService;
import com.adarsh.todoapp.service.UserService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	

	
	@PostMapping("/create")
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		Role roleResp=roleService.createRole(role);
		return ResponseEntity.ok(roleResp);
	}
	


}
