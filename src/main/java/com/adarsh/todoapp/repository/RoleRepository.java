package com.adarsh.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.todoapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
