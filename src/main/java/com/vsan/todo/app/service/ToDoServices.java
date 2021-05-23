package com.vsan.todo.app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsan.todo.app.dao.ToDo;
import com.vsan.todo.app.repository.ToDoRepo;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class ToDoServices {
	
	@Autowired
	private ToDoRepo repository;
	
	
	//Create ToDo - Update
	public ToDo createToDo(ToDo todo) {
		LocalDate data = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, dd-MMMM-yyyy");
		String dataCreazione = data.format(formatter);
		//setto la data di creazione;
		todo.setCreationDate(dataCreazione);
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
			log.error("Todo not present");
			return null;
		}
		return updatedToDo;
	}
	
	
	//Delete ToDo
	public void deleteToDo(Long id) {
		repository.deleteById(id);
		
		
	}
	
	public void completeToDo(Long id) {			
		ToDo todoCompleted = repository.findById(id).get();
		todoCompleted.setCompleted(!todoCompleted.isCompleted());
		repository.save(todoCompleted);
		
	}

}
