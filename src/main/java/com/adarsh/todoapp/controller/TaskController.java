package com.adarsh.todoapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adarsh.todoapp.model.Task;
import com.adarsh.todoapp.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/create")
	public ResponseEntity<Task> createTask( @RequestBody Task task){
		Task taskResponse=taskService.createTask(task);
		return ResponseEntity.ok(taskResponse);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task ){
		Task taskResponse=taskService.updateTask(task, id);
		return ResponseEntity.ok(taskResponse);
	}
	
	@GetMapping("/get")
	public ResponseEntity<ArrayList<Task>> getTask(){
		ArrayList<Task> tasks= taskService.getTask();
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("getId/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id){
		Task task=taskService.getTaskById(id);
		return ResponseEntity.ok(task);
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteTask( @PathVariable Long id){
		taskService.deleteTask(id);
	}
}
