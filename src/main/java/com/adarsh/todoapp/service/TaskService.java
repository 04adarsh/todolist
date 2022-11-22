package com.adarsh.todoapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adarsh.todoapp.exception.CustomException;
import com.adarsh.todoapp.model.Task;
import com.adarsh.todoapp.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	
	public Task createTask (Task task) {
		Task response=taskRepository.findByTaskTitle(task.getTaskTitle());
		if(response!=null) {
			throw new CustomException("task already exist");
		}
		System.out.println(task);
		response =taskRepository.save(task);
		System.out.println(response);
		return response;
		
	}
	
	public Task updateTask(Task task, Long id) {
		Task taskResponse=this.taskRepository.findById(id).orElseThrow(()-> new CustomException("task not found"));
		taskResponse.setTaskTitle(task.getTaskTitle());
		taskResponse.setTaskDescription(task.getTaskDescription());
		return taskRepository.save(taskResponse);
	}
	
	public ArrayList<Task> getTask(){
	ArrayList<Task> task=(ArrayList<Task>) taskRepository.findAll();
	return task;
	}
	
	public Task getTaskById(Long id) {
		Task task=taskRepository.findById(id).orElseThrow(()-> new CustomException("task not found"));
		return task;
	}
	
	public void deleteTask(Long id) {
		taskRepository.findById(id).orElseThrow(()-> new CustomException("Task does not exist"));
			taskRepository.deleteById(id);
	}
}
