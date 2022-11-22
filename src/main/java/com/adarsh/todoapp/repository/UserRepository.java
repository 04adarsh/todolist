package com.adarsh.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarsh.todoapp.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String> {

}
