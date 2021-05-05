package com.vsan.todo.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsan.todo.app.dao.ToDo;
import com.vsan.todo.app.repository.ToDoRepo;


@Service
public class ToDoServices {
	
	@Autowired
	private ToDoRepo repository;
	
	
	//Create ToDo - Update
	public ToDo createToDo(ToDo todo) {
		//setto la data di creazione;
		todo.setCreationDate(LocalDateTime.now());
		//salvo nel db;
		repository.save(todo);
		return todo;
	}
	 
	//Read ToDo
	public List<ToDo> readToDos(){
		List<ToDo> toDoList= repository.findAll();
		if(toDoList.size() > 0) {
			return toDoList;
		} else {
			return new ArrayList<ToDo>();
		}
	}
	
	//Update
	public ToDo updateToDo(ToDo todo) {
		
		ToDo updatedToDo = null;
		
		Optional<ToDo> toDoItem = repository.findById(todo.getId());

		if (toDoItem.isPresent()) {
			// se l'item è presente, allora lo modifico (update);
			updatedToDo = toDoItem.get();
			// setto i campi nel nuovo oggetto
			updatedToDo.setDescription(todo.getDescription());
			// salvo l'item modificato
			repository.save(updatedToDo);
		} else {
			createToDo(todo);
		}
		return updatedToDo;
	}
	
	
	//Delete ToDo
	public void deleteToDo(Long id) {
		repository.deleteById(id);
		
	}
	
	public void completeToDo(ToDo todo) {
		if(todo.isCompleted() == false) {
			todo.setCompleted(true);
		}
	}

}
