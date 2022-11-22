package com.adarsh.todoapp.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adarsh.todoapp.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String header=request.getHeader("Authorization");
		String jwtToken=null;
		String userName=null;
		
		if(header!=null && header.startsWith("Bearer ")) {
			jwtToken=header.substring(7);
			
			try {
				userName=jwtUtil.getUserNameFromToken(jwtToken);
			}catch(IllegalArgumentException e) {
				System.out.println("unable to get JWT Token");
			}catch(ExpiredJwtException e) {
				System.out.println("JWT is expired");
			}catch(Exception e) {
				System.out.println("something went wrong in jwtfilter");
			}
			
			if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails =userDetailsService.loadUserByUsername(userName);
				
				if(jwtUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, 
							null,userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}else {
			System.out.println("JWT does not starts with bearer");
		}
	
		
		filterChain.doFilter(request, response);
	
		
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	
}
