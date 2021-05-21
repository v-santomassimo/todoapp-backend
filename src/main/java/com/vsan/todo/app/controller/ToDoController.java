package com.vsan.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsan.todo.app.dao.ToDo;
import com.vsan.todo.app.repository.ToDoRepo;
import com.vsan.todo.app.service.ToDoServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/vsan/todo-app")
@CrossOrigin("*")
public class ToDoController {
	
	@Autowired
	private ToDoServices service;

	
	
	@GetMapping("/todo")
	public List<ToDo> getToDoItems(){
		List<ToDo> items = service.readToDos();
		return items;
	}
	
	@PostMapping("/addToDo")
	public ToDo addToDo(@RequestBody ToDo newTodo) {
		ToDo todo = service.createToDo(newTodo);
		log.info("Item added correctly");
		return todo; 
	}
	
	@PutMapping("/update")
	public ToDo updateToDo(@RequestBody ToDo todo) {
		ToDo newToDo = service.updateToDo(todo);
		return newToDo;
	}
	
	@DeleteMapping("/deleteToDo/{id}")
	public void deleteToDo(@PathVariable("id") Long id) {
		service.deleteToDo(id);
	}
	
	@PostMapping("/completed/{id}")
	public void completedItem(@PathVariable("id") Long id) {
		service.completeToDo(id);
	}
	

}
