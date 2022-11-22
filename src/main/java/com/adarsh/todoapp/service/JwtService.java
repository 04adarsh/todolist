package com.adarsh.todoapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adarsh.todoapp.exception.CustomException;
import com.adarsh.todoapp.model.JwtRequest;
import com.adarsh.todoapp.model.JwtResponse;
import com.adarsh.todoapp.model.UserModel;
import com.adarsh.todoapp.repository.UserRepository;
import com.adarsh.todoapp.utils.JwtUtil;

@Service
public class JwtService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) {
		String userName=jwtRequest.getUserName();
		String password=jwtRequest.getPassword();
		authenticate(userName, password);
		
		final UserDetails userDetails=loadUserByUsername(userName);
		String jwtToken=jwtUtil.generateToken(userDetails);
UserModel user=userRepository.findById(userName).get();
		return new JwtResponse(user,jwtToken);
		
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserModel user=userRepository.findById(username).get();
		if(user!=null) {
			return  new org.springframework.security.core.userdetails.User(
					user.getUserName(),
					user.getPassword(),
					getAuthorities(user)
					);
		}else {
			throw new UsernameNotFoundException("userName is not valid");
		}
	}
	
	private Set getAuthorities(UserModel user) {
		Set authorities=new HashSet();
		user.getRole().forEach(role->{
			authorities.add(new SimpleGrantedAuthority("ROLE"+role.getRoleName()));
		});
		return authorities;
	}
	
	private void authenticate(String userName, String password) {
		try {
			authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(userName, password));
		}catch(DisabledException e) {
			throw new CustomException("User is disabled");
		}catch(BadCredentialsException e) {
			throw new CustomException("Bad Credentials");
		}
		
	}

}
