package com.adarsh.todoapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adarsh.todoapp.model.Role;
import com.adarsh.todoapp.model.UserModel;
import com.adarsh.todoapp.repository.RoleRepository;
import com.adarsh.todoapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private RoleRepository roleRepository;
//
//public void userAndRoleInit() {
//	Role user =new Role();
//	user.setRoleName("USER");
//	user.setRoleDescription("This is defualt role for newly created users");
//	roleRepository.save(user);
//	Role admin=new Role();
//	admin.setRoleName("ADMIN");
//	admin.setRoleDescription("Admin role");
//	roleRepository.save(admin);
//	
//	Set<Role> userRole=new HashSet<>();
//	userRole.add(user);
//	Set<Role> adminRole=new HashSet<>();
//	adminRole.add(admin);
//	
//	User user1=new User();
//	User user2=new User();
//	user1.setFirstName("Adarsh");
//	user1.setLastName("Thakur");
//	user1.setEmail("04adarsh@gmail.com");
//	user1.setPassword(passwordEncoder.encode("adarsh@123"));
//	user1.setRole(userRole);
//	userRepository.save(user1);
//	
//	user2.setFirstName("pranit");
//	user2.setLastName("Yawalakr");
//	user2.setEmail("pmy@gmail.com");
//	user2.setPassword(passwordEncoder.encode("pmy@123"));
//	user2.setRole(adminRole);
//	userRepository.save(user2);
//}
	
	public UserModel createUser(UserModel user) {
		Role role=new Role();
		role.setRoleName("USER");
		role.setRoleDescription("This is default role for newely created users");
		
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		user.setRole(roles);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
}
