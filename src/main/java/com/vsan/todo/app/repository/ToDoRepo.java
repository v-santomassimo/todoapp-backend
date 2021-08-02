package com.vsan.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vsan.todo.app.dao.ToDo;


@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long> {

}
