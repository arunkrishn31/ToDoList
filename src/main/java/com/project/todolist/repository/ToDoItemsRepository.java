package com.project.todolist.repository;

import com.project.todolist.dto.ToDoItemsDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todolist.entity.ToDoItems;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemsRepository extends JpaRepository<ToDoItems, Integer> {

    List<ToDoItemsDto> findByToDoListId(int todolistid);
}
