package com.adarsh.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarsh.todoapp.model.Role;
import com.adarsh.todoapp.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

}
